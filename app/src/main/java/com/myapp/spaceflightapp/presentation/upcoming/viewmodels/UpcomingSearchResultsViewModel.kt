package com.myapp.spaceflightapp.presentation.upcoming.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.di.MainSearchScreenDI
import kotlinx.coroutines.launch

class UpcomingSearchResultsViewModel : ViewModel() {
    val results = MutableLiveData<List<String>>()

    private val interactor = MainSearchScreenDI.getUpcomingResultsInteractor()
    fun showResults() = viewModelScope.launch {
        results.value = interactor.getSearchUpcomingResults()
            .map { "${it.name} - ${it.launchDate}" }
    }
}