package com.launchapp.launches.feature_list.state

import com.launchapp.launches.domain.model.Launch

data class ListState(
    val launches: List<Launch> = emptyList(),
    val isLoading: Boolean = false,
    val isPaginationLoading: Boolean = false,
    val error: String? = null,
    val hasMore: Boolean = true,
    val cursor: String? = null
)