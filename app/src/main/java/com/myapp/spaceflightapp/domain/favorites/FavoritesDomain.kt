package com.myapp.spaceflightapp.domain.favorites

import com.myapp.spaceflightapp.core.ErrorType

sealed class FavoritesDomain {
    abstract fun <T> map(mapper: FavoritesDomainToUiMapper<T>): T

    data class Success(private val favorites: List<FavoriteDomain>) : FavoritesDomain() {
        override fun <T> map(mapper: FavoritesDomainToUiMapper<T>) = mapper.map(favorites)
    }

    data class Fail(private val errorType: ErrorType) : FavoritesDomain() {
        override fun <T> map(mapper: FavoritesDomainToUiMapper<T>) = mapper.map(errorType)
    }
}