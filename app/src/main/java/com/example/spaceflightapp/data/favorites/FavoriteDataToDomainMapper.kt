package com.example.spaceflightapp.data.favorites

import com.example.spaceflightapp.core.Abstract

interface FavoriteDataToDomainMapper<T> : Abstract.Mapper {
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
}