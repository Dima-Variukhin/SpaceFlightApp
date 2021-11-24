package com.example.spaceflightapp.data.apod.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.apod.ToApodMapper
import com.example.spaceflightapp.data.articles.ToArticleMapper
import com.example.spaceflightapp.data.articles.cloud.ArticleCloud
import com.google.gson.annotations.SerializedName

interface ApodCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToApodMapper<T>): T
    data class Base(
        @SerializedName("date")
        private val date: String,
        @SerializedName("explanation")
        private val explanation: String,
        @SerializedName("hdurl")
        private val url: String,
        @SerializedName("copyright")
        private val copyright: String,
        @SerializedName("title")
        private val title: String,
    ) : ApodCloud {
        override fun <T> map(mapper: ToApodMapper<T>) =
            mapper.map(date, explanation, url, copyright, title)
    }
}