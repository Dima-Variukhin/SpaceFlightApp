package com.example.spaceflightapp.domain.launches

import com.example.spaceflightapp.data.launches.LaunchData
import com.example.spaceflightapp.data.launches.LaunchesRepository

interface SearchResultsInteractor {
    suspend fun getSearchResults(year: String): List<LaunchData>

    class Base(
        private val launchesRepository: LaunchesRepository
    ) : SearchResultsInteractor {
        override suspend fun getSearchResults(year: String) = launchesRepository.getLaunches(year)
    }
}