package com.example.spaceflightapp.data.blogs.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface BlogService {
    @GET("blogs")
    suspend fun fetchBlogs(): ResponseBody
}