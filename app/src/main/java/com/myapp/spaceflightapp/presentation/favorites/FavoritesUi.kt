package com.myapp.spaceflightapp.presentation.favorites

import com.myapp.spaceflightapp.core.ListMapper

sealed class FavoritesUi {
    abstract fun map(mapper: ListMapper<FavoriteUi>)

    data class Base(private val favorites: MutableList<FavoriteUi>) : FavoritesUi() {
        override fun map(mapper: ListMapper<FavoriteUi>) = mapper.map(favorites)
    }
}