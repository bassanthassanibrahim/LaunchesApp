package com.launchapp.launches.domain.repository

import com.launchapp.launches.domain.model.LaunchDetail
import com.launchapp.launches.domain.model.LaunchesPage


interface LaunchesRepository {

    suspend fun getLaunches(
        pageSize: Int,
        after: String?
    ): LaunchesPage

    suspend fun getLaunchDetail(
        launchId: String
    ): LaunchDetail
}