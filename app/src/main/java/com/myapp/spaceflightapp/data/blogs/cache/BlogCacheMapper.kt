package com.myapp.spaceflightapp.data.blogs.cache

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.blogs.BlogData
import com.myapp.spaceflightapp.data.blogs.ToBlogMapper


interface BlogCacheMapper : Abstract.Mapper.Data<List<BlogDb>, List<BlogData>> {
    class Base(private val mapper: ToBlogMapper<BlogData>) : BlogCacheMapper {
        override fun map(data: List<BlogDb>) = data.map { blogDb -> blogDb.map(mapper) }
    }
}