package com.example.spaceflightapp.data.favorites

import com.example.spaceflightapp.core.Abstract


sealed class FavoritesData : Abstract.DataObject {
    abstract fun <T> map(mapper: FavoritesDataToDomainMapper<T>): T

    data class Success(private val favorites: List<FavoriteData>) : FavoritesData() {
        override fun <T> map(mapper: FavoritesDataToDomainMapper<T>) = mapper.map(favorites)
    }

    data class Fail(private val e: Exception) : FavoritesData() {
        override fun <T> map(mapper: FavoritesDataToDomainMapper<T>) = mapper.map(e)
    }
}