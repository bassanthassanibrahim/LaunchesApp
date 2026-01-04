package com.launchapp.launches.data

import com.launchapp.launches.data.repository.LaunchesRepositoryImpl
import com.launchapp.launches.domain.repository.LaunchesRepository
import javax.inject.Singleton
import dagger.Module
import dagger.Binds
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindLaunchesRepository(
        impl: LaunchesRepositoryImpl
    ): LaunchesRepository
}