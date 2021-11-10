package com.example.spaceflightapp.presentation

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import com.example.spaceflightapp.core.Communication

interface NavigationCommunication {
    fun observe(owner: LifecycleOwner, navigate: (Int) -> Unit)

    fun navigateTo(position: Int)

    class Base : NavigationCommunication {
        private val liveData = MutableLiveData<Int>()
        override fun observe(owner: LifecycleOwner, navigate: (Int) -> Unit) {
            liveData.observe(owner) { navigate.invoke(it) }
        }

        override fun navigateTo(position: Int) {
            liveData.value = position
        }
    }
}

interface NavigationCommunicationWeb : Communication<String> {
    class Base : Communication.Base<String>(), NavigationCommunicationWeb
}

