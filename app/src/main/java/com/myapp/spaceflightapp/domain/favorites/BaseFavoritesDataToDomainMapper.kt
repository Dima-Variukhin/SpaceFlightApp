package com.myapp.spaceflightapp.domain.favorites

import com.myapp.spaceflightapp.data.favorites.FavoriteData
import com.myapp.spaceflightapp.data.favorites.FavoriteDataToDomainMapper
import com.myapp.spaceflightapp.data.favorites.FavoritesDataToDomainMapper
import java.lang.Exception

class BaseFavoritesDataToDomainMapper(private val favoriteMapper: FavoriteDataToDomainMapper<FavoriteDomain>) :
    FavoritesDataToDomainMapper<FavoritesDomain>() {
    override fun map(data: List<FavoriteData>): FavoritesDomain {
        val domainList = mutableListOf<FavoriteDomain>()
        data.forEach { favoriteData ->
            domainList.add(favoriteData.map(favoriteMapper))
        }
        return FavoritesDomain.Success(domainList)
    }

    override fun map(e: Exception) = FavoritesDomain.Fail(errorType(e))
}