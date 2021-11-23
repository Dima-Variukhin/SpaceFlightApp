package com.example.spaceflightapp.domain.launches

import com.example.spaceflightapp.data.launches.LaunchData
import com.example.spaceflightapp.data.launches.LaunchesRepository


interface LaunchDetailsInteractor {
    suspend fun getLaunchData(year: String, position: Int): LaunchData

    class Base(
        private val repository: LaunchesRepository
    ) : LaunchDetailsInteractor {
        override suspend fun getLaunchData(year: String, position: Int) =
            repository.getLaunchData(year, position)
    }
}