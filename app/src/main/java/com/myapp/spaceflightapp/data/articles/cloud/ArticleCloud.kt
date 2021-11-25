package com.myapp.spaceflightapp.data.articles.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.articles.ToArticleMapper
import com.google.gson.annotations.SerializedName

interface ArticleCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToArticleMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val idA: Int,
        @SerializedName("title")
        private val titleA: String,
        @SerializedName("url")
        private val urlA: String,
        @SerializedName("imageUrl")
        private val imageUrlA: String,
        @SerializedName("newsSite")
        private val newsSiteA: String,
        @SerializedName("summary")
        private val summaryA: String,
        @SerializedName("publishedAt")
        private val publishedAtA: String,
        @SerializedName("updatedAt")
        private val updatedAtA: String,
    ) : ArticleCloud {
        override fun <T> map(mapper: ToArticleMapper<T>) =
            mapper.map(idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA)
    }
}