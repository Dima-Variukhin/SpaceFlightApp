package com.example.spaceflightapp.sl.articles

import com.example.spaceflightapp.core.RepositoryContainer
import com.example.spaceflightapp.data.articles.ArticleRepository
import com.example.spaceflightapp.data.articles.ToArticleMapper
import com.example.spaceflightapp.data.articles.cache.ArticleCacheDataSource
import com.example.spaceflightapp.data.articles.cache.ArticleCacheMapper
import com.example.spaceflightapp.data.articles.cache.ArticleDataToDbMapper
import com.example.spaceflightapp.data.articles.cloud.ArticleCloudDataSource
import com.example.spaceflightapp.data.articles.cloud.ArticleCloudMapper
import com.example.spaceflightapp.data.articles.cloud.ArticleService
import com.example.spaceflightapp.sl.core.CoreModule

class ArticlesRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<ArticleRepository> {
    override fun repository(): ArticleRepository {
        val toArticleMapper = ToArticleMapper.Base()
        return ArticleRepository.Base(
            articlesCloudDataSource(),
            articlesCacheDataSource(),
            ArticleCloudMapper.Base(toArticleMapper),
            ArticleCacheMapper.Base(toArticleMapper)
        )
    }

    private fun articlesCacheDataSource() =
        ArticleCacheDataSource.Base(coreModule.realmProvider, ArticleDataToDbMapper.Base())

    private fun articlesCloudDataSource() =
        ArticleCloudDataSource.Base(articlesService(), coreModule.gson)

    private fun articlesService() = coreModule.makeService(ArticleService::class.java)
}