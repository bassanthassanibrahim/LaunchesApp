package com.launchapp.launches.feature_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.launchapp.launches.core.network.NetworkMonitor
import com.launchapp.launches.domain.usecase.GetLaunchesUseCase
import com.launchapp.launches.feature_list.intent.ListIntent
import com.launchapp.launches.feature_list.state.ListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getLaunchesUseCase: GetLaunchesUseCase,
    private val networkMonitor: NetworkMonitor
) : ViewModel() {

    private val _state = MutableStateFlow(ListState())
    val state = _state.asStateFlow()

    init {
        observeNetwork()
        handleIntent(ListIntent.LoadInitial) }

    fun handleIntent(intent: ListIntent) {
        when (intent) {
            is ListIntent.LoadInitial -> loadData(isRefresh = false)
            is ListIntent.LoadMore -> if (!state.value.isPaginationLoading && state.value.hasMore) loadData(isRefresh = false, isPagination = true)
            is ListIntent.Refresh -> loadData(isRefresh = true)
        }
    }

    private fun loadData(isRefresh: Boolean, isPagination: Boolean = false) {
        viewModelScope.launch {
            if (isPagination) _state.update { it.copy(isPaginationLoading = true) }
            else _state.update { it.copy(isLoading = true, error = null) }

            try {
                val currentCursor = if (isRefresh) null else _state.value.cursor
                val result = getLaunchesUseCase(pageSize = 20, after = currentCursor)

                _state.update { it.copy(
                    launches = if (isRefresh) result.launches else it.launches + result.launches,
                    cursor = result.cursor,
                    hasMore = result.hasMore,
                    isLoading = false,
                    isPaginationLoading = false
                ) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, isPaginationLoading = false, error = e.message) }
            }
        }
    }
    private fun observeNetwork() {
        networkMonitor.isConnected
            .drop(1) // تجاهل الحالة الأولى عند فتح التطبيق
            .onEach { isConnected ->
                // إذا رجع النت وكان عندنا حالة خطأ أو القائمة فارغة، حدث البيانات
                if (isConnected && (state.value.error != null || state.value.launches.isEmpty())) {
                    handleIntent(ListIntent.Refresh)
                }
            }
            .launchIn(viewModelScope)
    }
}
