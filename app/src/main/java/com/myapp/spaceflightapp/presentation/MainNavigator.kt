package com.myapp.spaceflightapp.presentation

import com.myapp.spaceflightapp.core.Read

interface MainNavigator : Read<Int> {
    fun getFragment(id: Int): BaseFragment<*>
    fun navigateBack(navigationCommunication: NavigationCommunication)

}