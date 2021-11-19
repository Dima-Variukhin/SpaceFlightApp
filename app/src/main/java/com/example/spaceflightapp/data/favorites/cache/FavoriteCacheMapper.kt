package com.example.spaceflightapp.data.favorites.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.favorites.FavoriteData
import com.example.spaceflightapp.data.favorites.ToFavoriteMapper


interface FavoriteCacheMapper : Abstract.Mapper.Data<List<FavoriteDb>, List<FavoriteData>> {
    class Base(private val mapper: ToFavoriteMapper<FavoriteData>) : FavoriteCacheMapper {
        override fun map(data: List<FavoriteDb>) = data.map { favoriteDb -> favoriteDb.map(mapper) }
    }
}