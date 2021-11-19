package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.core.Abstract

interface ArticleDomainToUiMapper<T> : Abstract.Mapper {
    fun map(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String,
        data: String
    ): T
}