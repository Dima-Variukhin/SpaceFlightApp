package com.myapp.spaceflightapp.presentation.articles

import com.myapp.spaceflightapp.core.ErrorType
import com.myapp.spaceflightapp.core.ResourceProvider
import com.myapp.spaceflightapp.domain.articles.ArticleDomain
import com.myapp.spaceflightapp.domain.articles.ArticleDomainToUiMapper
import com.myapp.spaceflightapp.domain.articles.ArticlesDomainToUiMapper

class BaseArticlesDomainToUiMapper(
    resourceProvider: ResourceProvider,
    private val articleMapper: ArticleDomainToUiMapper<ArticleUi>
) : ArticlesDomainToUiMapper<ArticlesUi>(resourceProvider) {
    override fun map(data: List<ArticleDomain>) =
        ArticlesUi.Base(ArrayList(data.map { it.map(articleMapper) }))

    override fun map(errorType: ErrorType) =
        ArticlesUi.Base(ArrayList(listOf(ArticleUi.Fail(errorMessage((errorType))))))
}