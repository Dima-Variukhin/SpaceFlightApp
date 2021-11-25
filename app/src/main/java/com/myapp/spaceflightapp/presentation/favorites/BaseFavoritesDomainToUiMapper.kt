package com.myapp.spaceflightapp.presentation.favorites

import com.myapp.spaceflightapp.R
import com.myapp.spaceflightapp.core.ErrorType
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.domain.favorites.FavoriteDomain
import com.myapp.spaceflightapp.domain.favorites.FavoriteDomainToUiMapper
import com.myapp.spaceflightapp.domain.favorites.FavoritesDomainToUiMapper

class BaseFavoritesDomainToUiMapper(
    private val resourceProvider: ResourceProvider,
    private val favoriteMapper: FavoriteDomainToUiMapper<FavoriteUi>
) : FavoritesDomainToUiMapper<FavoritesUi>(resourceProvider) {
    override fun map(data: List<FavoriteDomain>) = if (data.isNotEmpty())
        FavoritesUi.Base(ArrayList(data.map { it.map(favoriteMapper) })) else
        FavoritesUi.Base(ArrayList(listOf(FavoriteUi.Fail(resourceProvider.string(R.string.no_cache)))))

    override fun map(errorType: ErrorType) =
        FavoritesUi.Base(ArrayList(listOf(FavoriteUi.Fail(errorMessage((errorType))))))
}