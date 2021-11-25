package com.myapp.spaceflightapp.data.blogs.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface BlogsCloudDataSource {
    suspend fun fetchBlogs(): List<BlogCloud>

    class Base(private val service: BlogService, private val gson: Gson) :
        BlogsCloudDataSource {
        override suspend fun fetchBlogs(): List<BlogCloud> = gson.fromJson(
            service.fetchBlogs().string(),
            object : TypeToken<List<BlogCloud.Base>>() {}.type
        )
    }
}