package com.launchapp.launches.data

import com.launchapp.launches.domain.model.Launch
import com.launchapp.launches.domain.model.LaunchDetail
import com.launchapp.launches.network.GetLaunchDetailsQuery
import com.launchapp.launches.network.GetLaunchesQuery

fun GetLaunchesQuery.Launch.toDomain() = Launch(
    id = id,
    rocketName = rocket?.name ?: "Unknown",
    missionName = mission?.name ?: "No Mission",
    missionPatchUrl = mission?.missionPatch
)

fun GetLaunchDetailsQuery.Launch.toDetailDomain(): LaunchDetail {
    return LaunchDetail(
        id = id,
        site = site ?: "Unknown Launch Site",
        rocketName = rocket?.name ?: "Unknown Rocket",
        rocketType = rocket?.type ?: "Unknown Type",
        missionName = mission?.name ?: "No Mission Name",
        missionPatchUrl = mission?.missionPatch,
        isBooked = isBooked
    )
}