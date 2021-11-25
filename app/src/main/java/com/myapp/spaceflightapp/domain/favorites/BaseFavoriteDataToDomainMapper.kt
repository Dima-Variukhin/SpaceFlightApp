package com.myapp.spaceflightapp.domain.favorites

import com.myapp.spaceflightapp.data.favorites.FavoriteDataToDomainMapper

class BaseFavoriteDataToDomainMapper : FavoriteDataToDomainMapper<FavoriteDomain> {
    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ) = FavoriteDomain.Base(
        id,
        title,
        url,
        imageUrl,
        newsSite,
        summary,
        publishedAt,
        updatedAt
    )
}