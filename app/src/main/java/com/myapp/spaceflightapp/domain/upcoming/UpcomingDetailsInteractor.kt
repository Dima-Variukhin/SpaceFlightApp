package com.myapp.spaceflightapp.domain.upcoming

import com.myapp.spaceflightapp.data.upcoming.UpcomingData
import com.myapp.spaceflightapp.data.upcoming.UpcomingRepository

interface UpcomingDetailsInteractor {
    suspend fun getUpcomingData(position: Int): UpcomingData

    class Base(
        private val repository: UpcomingRepository
    ) : UpcomingDetailsInteractor {
        override suspend fun getUpcomingData(position: Int) = repository.getUpcomingData(position)
    }
}