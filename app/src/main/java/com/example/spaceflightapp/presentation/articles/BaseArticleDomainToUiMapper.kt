package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.domain.articles.ArticleDomainToUiMapper

class BaseArticleDomainToUiMapper : ArticleDomainToUiMapper<ArticleUi> {
    override fun map(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String,
        data: String
    ) = ArticleUi.Base(
        idA,
        titleA,
        urlA,
        imageUrlA,
        newsSiteA,
        summaryA,
        publishedAtA,
        updatedAtA,
        data
    )
}