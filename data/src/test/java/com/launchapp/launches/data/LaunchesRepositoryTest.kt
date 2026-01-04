package com.launchapp.launches.data


import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.ApolloResponse
import com.launchapp.launches.data.repository.LaunchesRepositoryImpl
import com.launchapp.launches.network.GetLaunchesQuery
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.UUID

class LaunchesRepositoryTest {

    private val apolloClient: ApolloClient = mockk()
    private val repository = LaunchesRepositoryImpl(apolloClient)

    @Test
    fun `getLaunches should map Apollo data to Domain model correctly`() = runBlocking {
        val mockId = UUID.randomUUID().toString()
        val mockApolloLaunch = mockk<GetLaunchesQuery.Launch> {
            every { id } returns mockId
            every { rocket } returns mockk { every { name } returns "Falcon 9" }
            every { mission } returns mockk {
                every { name } returns "Starlink"
                every { missionPatch } returns "url"
            }
        }
    }
}