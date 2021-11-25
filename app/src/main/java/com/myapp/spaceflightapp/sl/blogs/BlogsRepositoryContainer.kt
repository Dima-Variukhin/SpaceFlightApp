package com.myapp.spaceflightapp.sl.blogs

import com.myapp.spaceflightapp.core.RepositoryContainer
import com.myapp.spaceflightapp.data.blogs.BlogRepository
import com.myapp.spaceflightapp.data.blogs.ToBlogMapper
import com.myapp.spaceflightapp.data.blogs.cache.BlogCacheMapper
import com.myapp.spaceflightapp.data.blogs.cache.BlogDataToDbMapper
import com.myapp.spaceflightapp.data.blogs.cache.BlogsCacheDataSource
import com.myapp.spaceflightapp.data.blogs.cloud.BlogCloudMapper
import com.myapp.spaceflightapp.data.blogs.cloud.BlogService
import com.myapp.spaceflightapp.data.blogs.cloud.BlogsCloudDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteCacheDataSource
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import com.myapp.spaceflightapp.sl.core.CoreModule

class BlogsRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<BlogRepository> {
    override fun repository(): BlogRepository {
        val toBlogMapper = ToBlogMapper.Base()
        return BlogRepository.Base(
            blogsCloudDataSource(),
            blogsCacheDataSource(),
            favoritesCacheDataSource(),
            BlogCloudMapper.Base(toBlogMapper),
            BlogCacheMapper.Base(toBlogMapper)
        )
    }

    private fun blogsCacheDataSource() =
        BlogsCacheDataSource.Base(coreModule.realmProvider, BlogDataToDbMapper.Base())
    private fun favoritesCacheDataSource() =
        FavoriteCacheDataSource.Base(coreModule.realmProvider, FavoriteDataToDbMapper.Base())

    private fun blogsCloudDataSource() =
        BlogsCloudDataSource.Base(blogsService(), coreModule.gson)

    private fun blogsService() = coreModule.makeService(BlogService::class.java)
}