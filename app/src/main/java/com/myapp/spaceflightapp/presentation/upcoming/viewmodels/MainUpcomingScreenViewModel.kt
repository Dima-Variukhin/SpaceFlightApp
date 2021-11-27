package com.myapp.spaceflightapp.presentation.upcoming.viewmodels

import androidx.annotation.IdRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.di.MainSearchScreenDI
import com.myapp.spaceflightapp.domain.launches.Status
import com.myapp.spaceflightapp.domain.upcoming.StatusUpcoming
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainUpcomingScreenViewModel : ViewModel() {
    var searchState = MutableLiveData<Int>()
    var progressState = MutableLiveData<Boolean>()
    var errorState = MutableLiveData<Int>()

    private val interactor = MainSearchScreenDI.getUpcomingInteractorImpl()
    private var job: Job? = null

    fun fetchUpcoming() {
        viewModelScope.debounceLaunch(100) {
            progressState.postValue(true)
            when (interactor.fetch()) {
                StatusUpcoming.NO_RESULTS -> showScreenWithId(R.id.no_results)
                StatusUpcoming.NO_CONNECTION -> showScreenWithId(R.id.no_connection)
                StatusUpcoming.SERVICE_UNAVAILABLE -> showScreenWithId(R.id.service_unavailable)
                StatusUpcoming.SUCCESS -> showScreenWithId(R.id.go_to_search_details)
            }
        }
    }

    fun fetchUp() = fetchUpcoming()


    private fun showScreenWithId(@IdRes id: Int) {
        progressState.postValue(false)
        searchState.postValue(id)
    }

    private fun CoroutineScope.debounceLaunch(
        time: Long,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        job?.cancel()
        return launch {
            delay(time)
            block()
        }.also {
            job = it
        }
    }
}