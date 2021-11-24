package com.example.spaceflightapp.presentation.launches.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.di.MainSearchScreenDI.getSearchResultsInteractor
import kotlinx.coroutines.launch

class SearchResultsViewModel : ViewModel() {
    val results = MutableLiveData<List<String>>()

    private val interactor = getSearchResultsInteractor()
    fun showResults(year: String) = viewModelScope.launch {
        results.value = interactor.getSearchResults(year).map { it.missionName }
    }
}