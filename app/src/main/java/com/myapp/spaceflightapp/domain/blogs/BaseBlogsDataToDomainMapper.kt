package com.myapp.spaceflightapp.domain.blogs

import com.myapp.spaceflightapp.data.blogs.BlogData
import com.myapp.spaceflightapp.data.blogs.BlogDataToDomainMapper
import com.myapp.spaceflightapp.data.blogs.BlogsDataToDomainMapper

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