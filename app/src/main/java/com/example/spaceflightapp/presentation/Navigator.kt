package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.PreferencesProvider
import com.example.spaceflightapp.core.Read
import com.example.spaceflightapp.core.Save
import com.example.spaceflightapp.presentation.articles.ArticlesFragment
import com.example.spaceflightapp.presentation.articles.ArticlesNavigator
import com.example.spaceflightapp.presentation.articles.BaseFragment

interface Navigator : Save<Int>, Read<Int>, MainNavigator, ArticlesNavigator {
    class Base(preferencesProvider: PreferencesProvider) : Navigator {
        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getFileName())
        }

        private val screens = listOf(
            ArticlesFragment::class.java
        )

        override fun read() = sharedPreferences.getInt(getCurrentScreenKey(), 0)
        override fun save(data: Int) =
            sharedPreferences.edit().putInt(getCurrentScreenKey(), data).apply()

        override fun getFragment(id: Int): BaseFragment<*> {
            return screens[id].newInstance()
        }

        override fun saveArticleScreen() = save(ARTICLES_SCREEN)


        private fun getFileName() = NAVIGATOR_FILE_NAME
        private fun getCurrentScreenKey() = CURRENT_SCREEN_KEY

        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
            const val ARTICLES_SCREEN = 0
        }
    }
}