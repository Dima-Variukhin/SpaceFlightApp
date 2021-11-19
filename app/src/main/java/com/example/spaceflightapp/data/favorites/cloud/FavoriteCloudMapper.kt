package com.example.spaceflightapp.data.favorites.cloud

import com.example.spaceflightapp.core.Abstract

import com.example.spaceflightapp.data.favorites.FavoriteData
import com.example.spaceflightapp.data.favorites.ToFavoriteMapper

interface FavoriteCloudMapper : Abstract.Mapper.Data<List<FavoriteCloud>, List<FavoriteData>> {
    class Base(private val favoriteMapper: ToFavoriteMapper<FavoriteData>) : FavoriteCloudMapper {
        override fun map(data: List<FavoriteCloud>): List<FavoriteData> {
            return emptyList()
        }
    }
}