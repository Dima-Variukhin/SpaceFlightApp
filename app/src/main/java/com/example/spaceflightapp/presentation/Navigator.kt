package com.example.spaceflightapp.presentation

import com.example.spaceflightapp.core.PreferencesProvider
import com.example.spaceflightapp.core.Read
import com.example.spaceflightapp.core.Save
import com.example.spaceflightapp.presentation.apod.ApodNavigator
import com.example.spaceflightapp.presentation.apod.PhotoOfTheDay
import com.example.spaceflightapp.presentation.articles.Articles
import com.example.spaceflightapp.presentation.articles.ArticlesNavigator
import com.example.spaceflightapp.presentation.blogs.Blogs
import com.example.spaceflightapp.presentation.blogs.BlogsNavigator
import com.example.spaceflightapp.presentation.favorites.Favorites
import com.example.spaceflightapp.presentation.favorites.FavoritesNavigator
import com.example.spaceflightapp.presentation.reports.Reports
import com.example.spaceflightapp.presentation.reports.ReportsNavigator

interface Navigator : NavigatorScreens {
    class Base(preferencesProvider: PreferencesProvider) : Navigator {
        private val sharedPreferences by lazy {
            preferencesProvider.provideSharedPreferences(getFileName())
        }

        private val screens = listOf(
            Articles::class.java,
            Blogs::class.java,
            Reports::class.java,
            Favorites::class.java,
            PhotoOfTheDay::class.java,

        )

        override fun read() = sharedPreferences.getInt(getCurrentScreenKey(), 0)
        override fun save(data: Int) =
            sharedPreferences.edit().putInt(getCurrentScreenKey(), data).apply()

        override fun getFragment(id: Int): BaseFragment<*> {
            return screens[id].newInstance()
        }

        override fun navigateBack(navigationCommunication: NavigationCommunication) =
            navigationCommunication.navigateTo(previousScreen())


        private fun previousScreen(): Int {
            val previous: Int
            val savedScreen = read()
            previous = if (savedScreen > 0) {
                savedScreen - 1
            } else {
                savedScreen
            }
            return previous
        }

        override fun saveArticleScreen() = save(ARTICLES_SCREEN)
        override fun saveBlogScreen() = save(BLOGS_SCREEN)
        override fun saveReportScreen() = save(REPORTS_SCREEN)
        override fun saveFavoriteScreen() = save(FAVORITES_SCREEN)
        override fun saveApodScreen() = save(APOD_SCREEN)

        private fun getFileName() = NAVIGATOR_FILE_NAME
        private fun getCurrentScreenKey() = CURRENT_SCREEN_KEY


        private companion object {
            const val NAVIGATOR_FILE_NAME = "navigation"
            const val CURRENT_SCREEN_KEY = "screenId"
            const val ARTICLES_SCREEN = 0
            const val BLOGS_SCREEN = 1
            const val REPORTS_SCREEN = 2
            const val FAVORITES_SCREEN = 3
            const val APOD_SCREEN = 4

        }
    }
}

interface ScreenPosition {
    fun save(position: Int)
    fun load(): Int
    class Base(private val navigator: Navigator) : ScreenPosition {
        override fun save(position: Int) {
            navigator.save(position)
        }

        override fun load() = navigator.read()
    }
}

interface NavigatorScreens : Save<Int>, Read<Int>, MainNavigator, ArticlesNavigator, BlogsNavigator,
    ReportsNavigator, FavoritesNavigator, ApodNavigator