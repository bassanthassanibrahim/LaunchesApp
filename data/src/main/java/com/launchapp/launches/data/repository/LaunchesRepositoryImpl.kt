package com.launchapp.launches.data.repository

import com.launchapp.launches.domain.model.LaunchesPage
import com.launchapp.launches.domain.repository.LaunchesRepository
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Optional
import com.launchapp.launches.data.toDetailDomain
import com.launchapp.launches.data.toDomain
import com.launchapp.launches.domain.model.LaunchDetail
import com.launchapp.launches.network.GetLaunchDetailsQuery
import com.launchapp.launches.network.GetLaunchesQuery
import javax.inject.Inject

class LaunchesRepositoryImpl @Inject constructor(
    private val apolloClient: ApolloClient
) : LaunchesRepository {

    override suspend fun getLaunches(pageSize: Int, after: String?): LaunchesPage {
        val response = apolloClient.query(GetLaunchesQuery(Optional.present(after), pageSize)).execute()

        // Handling Data & Error
        val data = response.data?.launches ?: throw Exception("Network Error")

        return LaunchesPage(
            launches = data.launches.filterNotNull().map { it.toDomain() },
            cursor = data.cursor,
            hasMore = data.hasMore
        )
    }

    override suspend fun getLaunchDetail(launchId: String): LaunchDetail {
        val response = apolloClient.query(GetLaunchDetailsQuery(launchId)).execute()
        val launch = response.data?.launch ?: throw Exception("Launch not found")

        return launch.toDetailDomain()
    }
}