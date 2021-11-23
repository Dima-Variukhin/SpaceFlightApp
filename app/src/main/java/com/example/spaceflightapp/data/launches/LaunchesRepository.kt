package com.example.spaceflightapp.data.launches

import com.example.spaceflightapp.data.launches.cloud.LaunchesDataMapper
import com.example.spaceflightapp.data.launches.datasource.LaunchesDataStoreFactory

interface LaunchesRepository {
    suspend fun getLaunches(year: String, reload: Boolean = false): List<LaunchData>
    suspend fun getLaunchData(year: String, id: Int): LaunchData

    class Base(
        private val launchesDataStoreFactory: LaunchesDataStoreFactory,
        private val launchDataMapper: LaunchesDataMapper
    ) : LaunchesRepository {
        override suspend fun getLaunches(year: String, reload: Boolean): List<LaunchData> {
            val priority =
                if (reload) LaunchesDataStoreFactory.Priority.CLOUD else LaunchesDataStoreFactory.Priority.CACHE
            val dataStore = launchesDataStoreFactory.create(year, priority)
            return launchDataMapper.map(dataStore.getLaunchCloudList(year))
        }

        override suspend fun getLaunchData(year: String, id: Int) = getLaunches(year)[id]
    }
}