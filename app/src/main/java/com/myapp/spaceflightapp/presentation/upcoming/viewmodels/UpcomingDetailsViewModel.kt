package com.myapp.spaceflightapp.presentation.upcoming.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.di.MainSearchScreenDI
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUi
import com.myapp.spaceflightapp.presentation.upcoming.UpcomingUiMapper
import kotlinx.coroutines.launch

class UpcomingDetailsViewModel : ViewModel() {
    val upcomingData = MutableLiveData<List<UpcomingUi<*>>>()
    private val interactor = MainSearchScreenDI.getUpcomingDetailsInteractor()
    private val mapper = UpcomingUiMapper()

    fun showData(position: Int?) = viewModelScope.launch {
        upcomingData.value = mapper.map(interactor.getUpcomingData(position ?: 0))
    }
}