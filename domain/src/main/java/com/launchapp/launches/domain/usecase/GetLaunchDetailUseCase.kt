package com.launchapp.launches.domain.usecase

import com.launchapp.launches.domain.repository.LaunchesRepository
import javax.inject.Inject


class GetLaunchDetailUseCase @Inject constructor(
    private val repository: LaunchesRepository
) {
    suspend operator fun invoke(launchId: String) =
        repository.getLaunchDetail(launchId)
}