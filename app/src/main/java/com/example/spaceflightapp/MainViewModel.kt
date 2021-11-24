package com.example.spaceflightapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.spaceflightapp.presentation.*


class MainViewModel(
    private val screenPosition: ScreenPosition,
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication,
    private val communicationWeb: NavigationCommunicationWeb,
    private val communicationShare: NavigationCommunicationShare
) : ViewModel() {

    fun init() {
        navigateTo(screenPosition.load())
    }

    fun save(position: Int) {
        screenPosition.save(position)
        navigateTo(position)
    }

    fun observe(owner: LifecycleOwner, navigate: (Int) -> Unit) {
        communication.observe(owner, navigate)
    }

    fun observeWeb(owner: LifecycleOwner, observer: Observer<String>) {
        communicationWeb.observe(owner, observer)
    }

    fun observeShare(owner: LifecycleOwner, observer: Observer<String>) {
        communicationShare.observe(owner, observer)

    }

    fun navigateBack() {
        navigator.navigateBack(communication)
    }

    private fun navigateTo(position: Int) {
        communication.navigateTo(position)
    }

    fun getFragment(id: Int) = navigator.getFragment(id)
}

