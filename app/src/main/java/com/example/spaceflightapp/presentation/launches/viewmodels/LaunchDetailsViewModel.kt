package com.example.spaceflightapp.presentation.launches.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spaceflightapp.di.MainScreenDI
import com.example.spaceflightapp.presentation.launches.LaunchUi
import com.example.spaceflightapp.presentation.launches.LaunchUiMapper
import kotlinx.coroutines.launch

class LaunchDetailsViewModel : ViewModel() {
    val launchData = MutableLiveData<List<LaunchUi<*>>>()
    private val interactor = MainScreenDI.getLaunchDetailsInteractor()
    private val mapper = LaunchUiMapper()

    fun showData(year: String, position: Int?) = viewModelScope.launch {
        launchData.value = mapper.map(interactor.getLaunchData(year, position ?: 0))
    }
}