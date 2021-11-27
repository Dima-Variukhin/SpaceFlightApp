package com.myapp.spaceflightapp.domain.upcoming

import com.myapp.spaceflightapp.core.NetworkConnectionException
import com.myapp.spaceflightapp.data.upcoming.UpcomingRepository


interface UpcomingInteractor {
    suspend fun fetch(): StatusUpcoming

    class Base(
        private val repository: UpcomingRepository,
    ) : UpcomingInteractor {
        override suspend fun fetch() = try {
            val list = repository.getUpcomings()
            if (list.isEmpty())
                StatusUpcoming.NO_RESULTS
            else
                StatusUpcoming.SUCCESS
        } catch (e: Exception) {
            if (e is NetworkConnectionException)
                StatusUpcoming.NO_CONNECTION
            else
                StatusUpcoming.SERVICE_UNAVAILABLE
        }
    }
}

enum class StatusUpcoming {
    NO_RESULTS,
    SERVICE_UNAVAILABLE,
    NO_CONNECTION,
    SUCCESS
}