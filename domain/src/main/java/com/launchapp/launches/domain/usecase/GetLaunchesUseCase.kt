package com.launchapp.launches.domain.usecase

import com.launchapp.launches.domain.repository.LaunchesRepository
import javax.inject.Inject

class GetLaunchesUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(
        pageSize: Int,
        after: String?
    ) = repository.getLaunches(pageSize, after)
}