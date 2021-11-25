package com.myapp.spaceflightapp.data.articles

import com.myapp.spaceflightapp.core.Abstract

interface ToArticleMapper<T> : Abstract.Mapper {
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

    class Base :
        ToArticleMapper<ArticleData> {
        override fun map(
            idA: Int,
            titleA: String,
            urlA: String,
            imageUrlA: String,
            newsSiteA: String,
            summaryA: String,
            publishedAtA: String,
            updatedAtA: String
        ) = ArticleData.Base(
            idA,
            titleA,
            urlA,
            imageUrlA,
            newsSiteA,
            summaryA,
            publishedAtA,
            updatedAtA
        )
    }
}