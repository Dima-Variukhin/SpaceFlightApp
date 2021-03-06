package com.myapp.spaceflightapp.domain.articles

import com.myapp.spaceflightapp.data.articles.ArticleData
import com.myapp.spaceflightapp.data.articles.ArticleDataToDomainMapper
import com.myapp.spaceflightapp.data.articles.ArticlesDataToDomainMapper
import java.lang.Exception


class BaseArticlesDataToDomainMapper(private val articleMapper: ArticleDataToDomainMapper<ArticleDomain>) :
    ArticlesDataToDomainMapper<ArticlesDomain>() {
    override fun map(data: List<ArticleData>): ArticlesDomain {
        val domainList = mutableListOf<ArticleDomain>()
        data.forEach { articleData ->
            domainList.add(articleData.map(articleMapper))
        }
        return ArticlesDomain.Success(domainList)
    }

    override fun map(e: Exception) = ArticlesDomain.Fail(errorType(e))
}