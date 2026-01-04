package com.launchapp.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.launchapp.detail.intent.DetailIntent
import com.launchapp.detail.state.DetailState
import com.launchapp.launches.domain.usecase.GetLaunchDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getLaunchDetailUseCase: GetLaunchDetailUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    fun handleIntent(intent: DetailIntent) {
        when (intent) {
            is DetailIntent.LoadDetails -> loadDetails(intent.id)
        }
    }

    private fun loadDetails(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }
            try {
                val detail = getLaunchDetailUseCase(id)
                _state.update { it.copy(launch = detail, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}