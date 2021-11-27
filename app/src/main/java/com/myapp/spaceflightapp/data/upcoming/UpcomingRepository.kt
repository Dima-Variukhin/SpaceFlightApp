package com.myapp.spaceflightapp.data.upcoming

import com.myapp.spaceflightapp.data.upcoming.cloud.UpcomingCloudToDataMapper
import com.myapp.spaceflightapp.data.upcoming.datasource.UpcomingDataStore

interface UpcomingRepository {
    suspend fun getUpcomings(reload: Boolean = false): List<UpcomingData>
    suspend fun getUpcomingData(id: Int): UpcomingData

    class Base(
        private val upcomingDataStore: UpcomingDataStore.CloudUpcomingDataStore,
        private val upcomingDataMapper: UpcomingCloudToDataMapper
    ) : UpcomingRepository {
        override suspend fun getUpcomings(reload: Boolean): List<UpcomingData> {
            return upcomingDataMapper.map(upcomingDataStore.getUpcomingCloudList())
        }

        override suspend fun getUpcomingData(id: Int) = getUpcomings()[id]
    }
}