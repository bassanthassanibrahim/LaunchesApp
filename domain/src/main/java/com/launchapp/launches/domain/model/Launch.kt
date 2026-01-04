package com.launchapp.launches.domain.model

data class Launch(
    val id: String,
    val rocketName: String,
    val missionName: String,
    val missionPatchUrl: String?
)