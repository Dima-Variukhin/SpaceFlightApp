package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.Read
import com.example.spaceflightapp.presentation.articles.BaseFragment

interface MainNavigator : Read<Int> {
    fun getFragment(id: Int): BaseFragment<*>
}