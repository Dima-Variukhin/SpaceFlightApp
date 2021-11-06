package com.example.spaceflightapp.data.articles

import com.example.spaceflightapp.core.Abstract

interface ArticleDataToDomainMapper<T> : Abstract.Mapper {
    fun map(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ): T
}