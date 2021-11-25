package com.myapp.spaceflightapp.domain.favorites

import com.myapp.spaceflightapp.core.Abstract

interface FavoriteDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
        data: String
    ): T
}