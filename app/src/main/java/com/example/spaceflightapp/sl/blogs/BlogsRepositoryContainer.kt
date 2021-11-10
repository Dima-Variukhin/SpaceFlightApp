package com.example.spaceflightapp.sl.blogs

import com.example.spaceflightapp.core.RepositoryContainer
import com.example.spaceflightapp.data.blogs.BlogRepository
import com.example.spaceflightapp.data.blogs.ToBlogMapper
import com.example.spaceflightapp.data.blogs.cache.BlogCacheMapper
import com.example.spaceflightapp.data.blogs.cache.BlogDataToDbMapper
import com.example.spaceflightapp.data.blogs.cache.BlogsCacheDataSource
import com.example.spaceflightapp.data.blogs.cloud.BlogCloudMapper
import com.example.spaceflightapp.data.blogs.cloud.BlogService
import com.example.spaceflightapp.data.blogs.cloud.BlogsCloudDataSource
import com.example.spaceflightapp.sl.core.CoreModule

class BlogsRepositoryContainer(
    private val coreModule: CoreModule
) : RepositoryContainer<BlogRepository> {
    override fun repository(): BlogRepository {
        val toBlogMapper = ToBlogMapper.Base()
        return BlogRepository.Base(
            blogsCloudDataSource(),
            blogsCacheDataSource(),
            BlogCloudMapper.Base(toBlogMapper),
            BlogCacheMapper.Base(toBlogMapper)
        )
    }

    private fun blogsCacheDataSource() =
        BlogsCacheDataSource.Base(coreModule.realmProvider, BlogDataToDbMapper.Base())

    private fun blogsCloudDataSource() =
        BlogsCloudDataSource.Base(blogsService(), coreModule.gson)

    private fun blogsService() = coreModule.makeService(BlogService::class.java)
}