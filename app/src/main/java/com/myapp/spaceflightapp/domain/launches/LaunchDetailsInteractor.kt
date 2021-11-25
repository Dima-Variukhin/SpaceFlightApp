package com.myapp.spaceflightapp.domain.launches

import com.myapp.spaceflightapp.data.launches.LaunchData
import com.myapp.spaceflightapp.data.launches.LaunchesRepository


interface LaunchDetailsInteractor {
    suspend fun getLaunchData(year: String, position: Int): LaunchData

    class Base(
        private val repository: LaunchesRepository
    ) : LaunchDetailsInteractor {
        override suspend fun getLaunchData(year: String, position: Int) =
            repository.getLaunchData(year, position)
    }
}