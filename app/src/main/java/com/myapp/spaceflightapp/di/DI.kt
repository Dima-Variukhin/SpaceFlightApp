package com.myapp.spaceflightapp.di

import android.app.Application

object DI {

    fun initialize(app: Application) {
        NetworkDI.initializeSearch()
        MainSearchScreenDI.initialize(app)
    }
}