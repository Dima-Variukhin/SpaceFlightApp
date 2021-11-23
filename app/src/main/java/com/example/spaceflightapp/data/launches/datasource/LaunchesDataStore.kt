package com.example.spaceflightapp.data.launches.datasource

import com.example.spaceflightapp.core.ServerUnavailableException
import com.example.spaceflightapp.data.launches.cache.LaunchesCache
import com.example.spaceflightapp.data.launches.cloud.LaunchesCloud
import com.example.spaceflightapp.data.launches.cloud.LaunchesService

interface LaunchesDataStore {
    suspend fun getLaunchCloudList(year: String): List<LaunchesCloud>
    suspend fun getLaunchesDetails(year: String, id: Int): LaunchesCloud

    class CacheLaunchesDataStore(
        private val launchesCache: LaunchesCache
    ) : LaunchesDataStore {
        override suspend fun getLaunchCloudList(year: String) = launchesCache.get(year)

        override suspend fun getLaunchesDetails(year: String, id: Int) =
            getLaunchCloudList(year)[id]
    }

    class CloudLaunchesDataStore(
        private val launchesService: LaunchesService,
        private val launchesCache: LaunchesCache
    ) : LaunchesDataStore {
        override suspend fun getLaunchCloudList(year: String): List<LaunchesCloud> {
            val launches: List<LaunchesCloud>
            try {
                val launchesAsync = launchesService.getLaunchesAsync(year)
                val result = launchesAsync.await()
                launches = result.body()!!
                launchesCache.put(year, launches)
            } catch (e: Exception) {
                throw ServerUnavailableException()
            }
            return launches
        }

        override suspend fun getLaunchesDetails(year: String, id: Int) =
            throw UnsupportedOperationException("Operation is not available")
    }
}