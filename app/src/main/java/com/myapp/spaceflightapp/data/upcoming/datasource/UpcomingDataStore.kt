package com.myapp.spaceflightapp.data.upcoming.datasource

import com.myapp.spaceflightapp.core.ServerUnavailableException
import com.myapp.spaceflightapp.data.upcoming.cloud.UpcomingCloud
import com.myapp.spaceflightapp.data.upcoming.cloud.UpcomingService

interface UpcomingDataStore {
    suspend fun getUpcomingCloudList(): List<UpcomingCloud>
    suspend fun getUpcomingDetails(id: Int): UpcomingCloud

    class CloudUpcomingDataStore(
        private val upcomingService: UpcomingService,
    ) : UpcomingDataStore {
        override suspend fun getUpcomingCloudList(): List<UpcomingCloud> {
            val upcoming: List<UpcomingCloud>
            try {
                val upcomingAsync = upcomingService.getLaunchesAsync()
                val result = upcomingAsync.await()
                upcoming = result.body()!!
            } catch (e: Exception) {
                throw ServerUnavailableException()
            }
            return upcoming
        }

        override suspend fun getUpcomingDetails(id: Int) =
            throw UnsupportedOperationException("Operation is not available")
    }
}