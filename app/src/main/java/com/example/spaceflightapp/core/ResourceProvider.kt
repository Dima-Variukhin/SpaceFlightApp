package com.example.spaceflightapp.core

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes

interface ResourceProvider : PreferencesProvider {
    fun string(@StringRes id: Int): String
    fun string(@StringRes id: Int, vararg args: Any): String

    class Base(private var context: Context) : ResourceProvider {
        override fun string(id: Int) = context.getString(id)
        override fun string(id: Int, vararg args: Any) = context.getString(id, *args)

        override fun provideSharedPreferences(name: String): SharedPreferences =
            context.getSharedPreferences(name, Context.MODE_PRIVATE)

    }
}
