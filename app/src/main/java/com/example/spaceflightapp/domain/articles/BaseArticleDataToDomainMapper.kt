package com.example.spaceflightapp.domain.articles

import com.example.spaceflightapp.data.articles.ArticleDataToDomainMapper

class BaseArticleDataToDomainMapper : ArticleDataToDomainMapper<ArticleDomain> {
    override fun map(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String
    ) = ArticleDomain.Base(
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