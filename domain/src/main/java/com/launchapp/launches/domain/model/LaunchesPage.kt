package com.launchapp.launches.domain.model

data class LaunchesPage(
    val launches: List<Launch>,
    val cursor: String?,
    val hasMore: Boolean
)