package com.launchapp.launches.domain.model

data class LaunchDetail(
    val id: String,
    val site: String?,
    val rocketName: String,
    val rocketType: String,
    val missionName: String,
    val missionPatchUrl: String?,
    val isBooked: Boolean
)