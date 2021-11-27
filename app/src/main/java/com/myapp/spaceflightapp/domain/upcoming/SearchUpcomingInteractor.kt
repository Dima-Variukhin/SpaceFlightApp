package com.myapp.spaceflightapp.domain.upcoming

import com.myapp.spaceflightapp.data.upcoming.UpcomingData
import com.myapp.spaceflightapp.data.upcoming.UpcomingRepository

interface SearchUpcomingInteractor {
    suspend fun getSearchUpcomingResults(): List<UpcomingData>

    class Base(
        private val upcomingRepository: UpcomingRepository
    ) : SearchUpcomingInteractor {
        override suspend fun getSearchUpcomingResults() = upcomingRepository.getUpcomings()
    }
}