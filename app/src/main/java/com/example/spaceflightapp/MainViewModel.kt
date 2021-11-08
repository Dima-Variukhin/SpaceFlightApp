package com.example.spaceflightapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.spaceflightapp.presentation.MainNavigator
import com.example.spaceflightapp.presentation.NavigationCommunication
import com.example.spaceflightapp.presentation.NavigationCommunicationWeb

class MainViewModel(
    private val mainNavigator: MainNavigator,
    private val communication: NavigationCommunication,
    private val communicationWeb: NavigationCommunicationWeb
) : ViewModel() {

    fun init() {
        communication.map(mainNavigator.read())
    }

    fun save(position: Int) {
        mainNavigator.getFragment(position)
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun observeWeb(owner: LifecycleOwner, observer: Observer<String>) {
        communicationWeb.observe(owner, observer)
    }

    fun getFragment(id: Int) = mainNavigator.getFragment(id)

}