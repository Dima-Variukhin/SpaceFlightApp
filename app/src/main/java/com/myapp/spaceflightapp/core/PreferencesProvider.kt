package com.myapp.spaceflightapp.core

import android.content.SharedPreferences

interface PreferencesProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}