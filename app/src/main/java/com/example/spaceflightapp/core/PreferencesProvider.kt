package com.example.spaceflightapp.core

import android.content.SharedPreferences

interface PreferencesProvider {
    fun provideSharedPreferences(name: String): SharedPreferences
}