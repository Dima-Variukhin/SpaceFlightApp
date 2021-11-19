package com.example.spaceflightapp.data.favorites

import com.example.spaceflightapp.core.Abstract


interface ToFavoriteMapper<T> : Abstract.Mapper {
    fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String
    ): T

    class Base :
        ToFavoriteMapper<FavoriteData> {
        override fun map(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = FavoriteData.Base(
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
}