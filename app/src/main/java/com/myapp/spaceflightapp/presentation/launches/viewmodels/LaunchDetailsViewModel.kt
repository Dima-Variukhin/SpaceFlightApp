package com.myapp.spaceflightapp.presentation.launches.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.di.MainSearchScreenDI
import com.myapp.spaceflightapp.presentation.launches.LaunchUi
import com.myapp.spaceflightapp.presentation.launches.LaunchUiMapper
import kotlinx.coroutines.launch

class LaunchDetailsViewModel : ViewModel() {
    val launchData = MutableLiveData<List<LaunchUi<*>>>()
    private val interactor = MainSearchScreenDI.getLaunchDetailsInteractor()
    private val mapper = LaunchUiMapper()

    fun showData(year: String, position: Int?) = viewModelScope.launch {
        launchData.value = mapper.map(interactor.getLaunchData(year, position ?: 0))
    }
}