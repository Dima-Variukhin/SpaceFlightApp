package com.example.spaceflightapp

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.spaceflightapp.presentation.*
import com.example.spaceflightapp.presentation.articles.ArticlesNavigator
import com.example.spaceflightapp.presentation.blogs.BlogsNavigator

class MainViewModel(
    private val screenPosition: ScreenPosition,
    private val mainNavigator: MainNavigator,
    private val communication: NavigationCommunication,
    private val communicationWeb: NavigationCommunicationWeb
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

    private fun navigateTo(position: Int) {
        communication.navigateTo(position)
    }

    fun getFragment(id: Int) = mainNavigator.getFragment(id)

}

