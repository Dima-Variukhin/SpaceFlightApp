package com.myapp.spaceflightapp.presentation.favorites

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.myapp.spaceflightapp.core.Show
import com.myapp.spaceflightapp.domain.favorites.FavoritesDomainToUiMapper
import com.myapp.spaceflightapp.domain.favorites.FavoritesInteractor
import com.myapp.spaceflightapp.presentation.BaseViewModel
import com.myapp.spaceflightapp.presentation.NavigationCommunicationShare
import com.myapp.spaceflightapp.presentation.NavigationCommunicationWeb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val favoritesInteractor: FavoritesInteractor,
    private val mapper: FavoritesDomainToUiMapper<FavoritesUi>,
    private val communication: FavoritesCommunication,
    private val navigator: FavoritesNavigator,
    private val navigationCommunicationWeb: NavigationCommunicationWeb,
    private val navigationCommunicationShare: NavigationCommunicationShare,
) : BaseViewModel(), Show {
    fun fetchFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val resultDomain = favoritesInteractor.fetchFavorites()
            val resultUi = resultDomain.map(mapper)
            withContext(Dispatchers.Main) {
                communication.map(resultUi)
            }
        }
    }

    fun observe(owner: LifecycleOwner, observer: Observer<FavoritesUi>) {
        communication.observe(owner, observer)
    }

    fun initFavorites() {
        navigator.saveFavoriteScreen()
        fetchFavorites()
    }

    override fun open(data: String) {
        navigationCommunicationWeb.map(data)
    }

    override fun share(data: String) {
        navigationCommunicationShare.map(data)
    }

    override fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) {
        fetchFavorites()
        viewModelScope.launch(Dispatchers.IO) {
            favoritesInteractor.changeFavorite(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt
            )
            viewModelScope.launch(Dispatchers.Main) {
                fetchFavorites()
            }
        }
    }
}
