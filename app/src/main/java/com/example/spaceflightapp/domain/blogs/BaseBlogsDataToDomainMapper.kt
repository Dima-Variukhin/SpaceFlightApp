package com.example.spaceflightapp.domain.blogs

import com.example.spaceflightapp.data.blogs.BlogData
import com.example.spaceflightapp.data.blogs.BlogDataToDomainMapper
import com.example.spaceflightapp.data.blogs.BlogsDataToDomainMapper

import java.lang.Exception

class BaseBlogsDataToDomainMapper(private val blogMapper: BlogDataToDomainMapper<BlogDomain>) :
    BlogsDataToDomainMapper<BlogsDomain>() {
    override fun map(data: List<BlogData>): BlogsDomain {
        val domainList = mutableListOf<BlogDomain>()
        data.forEach { blogData ->
            domainList.add(blogData.map(blogMapper))
        }
        return BlogsDomain.Success(domainList)
    }

    override fun map(e: Exception) = BlogsDomain.Fail(errorType(e))
}