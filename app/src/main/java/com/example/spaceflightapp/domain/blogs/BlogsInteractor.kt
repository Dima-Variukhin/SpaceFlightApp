package com.example.spaceflightapp.domain.blogs

import com.example.spaceflightapp.data.blogs.BlogRepository
import com.example.spaceflightapp.data.blogs.BlogsDataToDomainMapper


interface BlogsInteractor {
    suspend fun fetchBlogs(): BlogsDomain
    suspend fun update(): BlogsDomain

    class Base(
        private val blogRepository: BlogRepository,
        private val mapper: BlogsDataToDomainMapper<BlogsDomain>
    ) : BlogsInteractor {
        override suspend fun fetchBlogs() = blogRepository.fetchData().map(mapper)
        override suspend fun update() = blogRepository.update().map(mapper)
    }
}