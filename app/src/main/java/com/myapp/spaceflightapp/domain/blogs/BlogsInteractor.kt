package com.myapp.spaceflightapp.domain.blogs

import com.myapp.spaceflightapp.data.blogs.BlogRepository
import com.myapp.spaceflightapp.data.blogs.BlogsDataToDomainMapper


interface BlogsInteractor {
    suspend fun fetchBlogs(): BlogsDomain
    suspend fun update(): BlogsDomain
    suspend fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
    )

    class Base(
        private val blogRepository: BlogRepository,
        private val mapper: BlogsDataToDomainMapper<BlogsDomain>
    ) : BlogsInteractor {
        override suspend fun fetchBlogs() = blogRepository.fetchData().map(mapper)
        override suspend fun update() = blogRepository.update().map(mapper)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = blogRepository.changeFavorite(
            id,
            title,
            url,
            imageUrl,
            newsSite,
            summary,
            publishedAt,
            updatedAt
        )
    }
}