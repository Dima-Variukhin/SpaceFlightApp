package com.myapp.spaceflightapp.domain.launches

import com.myapp.spaceflightapp.data.launches.LaunchData
import com.myapp.spaceflightapp.data.launches.LaunchesRepository

interface SearchResultsInteractor {
    suspend fun getSearchResults(year: String): List<LaunchData>

    class Base(
        private val launchesRepository: LaunchesRepository
    ) : SearchResultsInteractor {
        override suspend fun getSearchResults(year: String) = launchesRepository.getLaunches(year)
    }
}