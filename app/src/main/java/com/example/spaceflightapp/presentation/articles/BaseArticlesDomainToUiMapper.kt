package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.core.ErrorType
import com.example.spaceflightapp.core.ResourceProvider
import com.example.spaceflightapp.domain.articles.ArticleDomain
import com.example.spaceflightapp.domain.articles.ArticleDomainToUiMapper
import com.example.spaceflightapp.domain.articles.ArticlesDomainToUiMapper

class BaseArticlesDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val articleMapper: ArticleDomainToUiMapper<ArticleUi>
) : ArticlesDomainToUiMapper<ArticlesUi>(resourceProvider) {
    override fun map(data: List<ArticleDomain>) =
        ArticlesUi.Base(ArrayList(data.map { it.map(articleMapper) }))

    override fun map(errorType: ErrorType) =
        ArticlesUi.Base(ArrayList(listOf(ArticleUi.Fail(errorMessage((errorType))))))
}