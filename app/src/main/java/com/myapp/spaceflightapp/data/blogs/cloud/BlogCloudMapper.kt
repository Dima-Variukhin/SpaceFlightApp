package com.myapp.spaceflightapp.data.blogs.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.blogs.BlogData
import com.myapp.spaceflightapp.data.blogs.ToBlogMapper


interface BlogCloudMapper : Abstract.Mapper.Data<List<BlogCloud>, List<BlogData>> {
    class Base(private val blogMapper: ToBlogMapper<BlogData>) : BlogCloudMapper {
        override fun map(data: List<BlogCloud>) =
            data.map { blogData ->
                blogData.map(blogMapper)
            }
    }
}