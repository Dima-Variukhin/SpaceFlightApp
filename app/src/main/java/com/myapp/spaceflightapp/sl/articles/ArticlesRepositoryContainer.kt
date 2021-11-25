package com.myapp.spaceflightapp.sl.articles

import com.myapp.spaceflightapp.core.RepositoryContainer
import com.myapp.spaceflightapp.data.articles.ArticleRepository
import com.myapp.spaceflightapp.data.articles.ToArticleMapper
import com.myapp.spaceflightapp.data.articles.cache.ArticleCacheDataSource
import com.myapp.spaceflightapp.data.articles.cache.ArticleCacheMapper
import com.myapp.spaceflightapp.data.articles.cache.ArticleDataToDbMapper
import com.myapp.spaceflightapp.data.articles.cloud.ArticleCloudDataSource
import com.myapp.spaceflightapp.data.articles.cloud.ArticleCloudMapper
import com.myapp.spaceflightapp.data.articles.cloud.ArticleService
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.myapp.spaceflightapp.sl.core.CoreModule

class ArticlesRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<ArticleRepository> {
    override fun repository(): ArticleRepository {
        val toBlogMapper = ToArticleMapper.Base()
        return ArticleRepository.Base(
            articlesCloudDataSource(),
            articlesCacheDataSource(),
            favoritesCacheDataSource(), ArticleCloudMapper.Base(toBlogMapper),
            ArticleCacheMapper.Base(toBlogMapper)
        )
    }

    private fun articlesCacheDataSource() =
        ArticleCacheDataSource.Base(coreModule.realmProvider, ArticleDataToDbMapper.Base())

    private fun favoritesCacheDataSource() =
        FavoriteCacheDataSource.Base(coreModule.realmProvider, FavoriteDataToDbMapper.Base())

    private fun articlesCloudDataSource() =
        ArticleCloudDataSource.Base(articlesService(), coreModule.gson)

    private fun articlesService() = coreModule.makeService(ArticleService::class.java)
}