package com.example.spaceflightapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.spaceflightapp.presentation.MainNavigator
import com.example.spaceflightapp.presentation.NavigationCommunication

class MainViewModel(
    private val navigator: MainNavigator,
    private val communication: NavigationCommunication
) : ViewModel() {

    fun init() {
        communication.map(navigator.read())
    }

    fun observe(owner: LifecycleOwner, observer: Observer<Int>) {
        communication.observe(owner, observer)
    }

    fun getFragment(id: Int) = navigator.getFragment(id)
}