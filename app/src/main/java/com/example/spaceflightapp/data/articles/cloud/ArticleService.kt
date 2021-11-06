package com.example.spaceflightapp.data.articles.cloud

import okhttp3.ResponseBody
import retrofit2.http.GET

interface ArticleService {
    @GET("articles")
    suspend fun fetchArticles(): ResponseBody
}