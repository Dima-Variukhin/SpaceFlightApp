package com.myapp.spaceflightapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.myapp.spaceflightapp.di.DI
import com.myapp.spaceflightapp.sl.core.CoreModule
import com.myapp.spaceflightapp.sl.core.DependencyContainer
import com.myapp.spaceflightapp.sl.core.ViewModelsFactory

class SpaceFlightApp : Application() {
    private val coreModule = CoreModule()

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule))
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init( this)
        DI.initialize(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}