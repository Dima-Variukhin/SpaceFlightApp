package com.myapp.spaceflightapp.data.articles.cloud

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

interface ArticleCloudDataSource {
    suspend fun fetchArticles(): List<ArticleCloud>

    class Base(
        private val service: ArticleService,
        private val gson: Gson
    ) : ArticleCloudDataSource {
        override suspend fun fetchArticles(): List<ArticleCloud> = gson.fromJson(
            service.fetchArticles().string(),
            object : TypeToken<List<ArticleCloud.Base>>() {}.type
        )
    }
}