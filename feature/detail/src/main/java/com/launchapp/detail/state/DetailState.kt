package com.launchapp.detail.state

import com.launchapp.launches.domain.model.LaunchDetail

data class DetailState(
    val launch: LaunchDetail? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)