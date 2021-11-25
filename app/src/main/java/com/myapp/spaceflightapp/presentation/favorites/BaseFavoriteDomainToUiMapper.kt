package com.myapp.spaceflightapp.presentation.favorites

import com.myapp.spaceflightapp.domain.favorites.FavoriteDomainToUiMapper

class BaseFavoriteDomainToUiMapper : FavoriteDomainToUiMapper<FavoriteUi> {
    override fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
        data: String
    ) = FavoriteUi.Base(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, data)
}