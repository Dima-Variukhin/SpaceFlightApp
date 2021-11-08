package com.example.spaceflightapp.data.blogs.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.blogs.BlogData
import com.example.spaceflightapp.data.blogs.ToBlogMapper


interface BlogCacheMapper : Abstract.Mapper.Data<List<BlogDb>, List<BlogData>> {
    class Base(private val mapper: ToBlogMapper<BlogData>) : BlogCacheMapper {
        override fun map(data: List<BlogDb>) = data.map { blogDb -> blogDb.map(mapper) }
    }
}