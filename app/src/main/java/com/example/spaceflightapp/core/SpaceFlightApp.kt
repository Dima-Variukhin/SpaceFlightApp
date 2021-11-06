package com.example.spaceflightapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.example.spaceflightapp.sl.core.CoreModule
import com.example.spaceflightapp.sl.core.DependencyContainer
import com.example.spaceflightapp.sl.core.ViewModelsFactory
import java.security.acl.Owner

class SpaceFlightApp : Application() {
    private val coreModule = CoreModule()

    private val factory by lazy {
        ViewModelsFactory(DependencyContainer.Base(coreModule))
    }

    override fun onCreate() {
        super.onCreate()
        coreModule.init(this)
    }

    fun <T : ViewModel> viewModel(modelClass: Class<T>, owner: ViewModelStoreOwner): T =
        ViewModelProvider(owner, factory).get(modelClass)
}