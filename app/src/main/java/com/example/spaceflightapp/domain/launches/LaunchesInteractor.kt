package com.example.spaceflightapp.domain.launches

import com.example.spaceflightapp.core.NetworkConnectionException
import com.example.spaceflightapp.data.launches.LaunchesRepository
import java.util.*

interface LaunchesInteractor {
    fun isInputDataValid(year: String?): Boolean?
    suspend fun fetch(year: String): Status

    class Base(
        private val repository: LaunchesRepository,
        private val yearValidator: Validator.Base
    ) : LaunchesInteractor {
        override fun isInputDataValid(year: String?) = yearValidator.isValid(year)

        override suspend fun fetch(year: String): Status {
            val reload = Calendar.getInstance().get(Calendar.YEAR).toString() == year
            return getData(year, reload)
        }

        private suspend fun getData(year: String, reload: Boolean): Status =
            try {
                val list = repository.getLaunches(year, reload)
                if (list.isEmpty())
                    Status.NO_RESULTS
                else
                    Status.SUCCESS
            } catch (e: Exception) {
                if (e is NetworkConnectionException)
                    Status.NO_CONNECTION
                else
                    Status.SERVICE_UNAVAILABLE
            }
    }
}

enum class Status {
    NO_RESULTS,
    SERVICE_UNAVAILABLE,
    NO_CONNECTION,
    SUCCESS
}