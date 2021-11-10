package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.Read

interface MainNavigator : Read<Int> {
    fun getFragment(id: Int): BaseFragment<*>
}