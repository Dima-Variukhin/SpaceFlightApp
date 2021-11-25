package com.myapp.spaceflightapp.data.favorites.cache

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.favorites.FavoriteData
import com.myapp.spaceflightapp.data.favorites.ToFavoriteMapper


interface FavoriteCacheMapper : Abstract.Mapper.Data<List<FavoriteDb>, List<FavoriteData>> {
    class Base(private val mapper: ToFavoriteMapper<FavoriteData>) : FavoriteCacheMapper {
        override fun map(data: List<FavoriteDb>) = data.map { favoriteDb -> favoriteDb.map(mapper) }
    }
}