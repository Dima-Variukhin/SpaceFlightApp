package com.example.spaceflightapp.data.blogs.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.blogs.BlogData
import com.example.spaceflightapp.data.blogs.ToBlogMapper


interface BlogCloudMapper : Abstract.Mapper.Data<List<BlogCloud>, List<BlogData>> {
    class Base(private val blogMapper: ToBlogMapper<BlogData>) : BlogCloudMapper {
        override fun map(data: List<BlogCloud>) =
            data.map { blogData ->
                blogData.map(blogMapper)
            }
    }
}