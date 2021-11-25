package com.myapp.spaceflightapp.data.blogs.cloud

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.blogs.ToBlogMapper
import com.google.gson.annotations.SerializedName

interface BlogCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToBlogMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val idB: Int,
        @SerializedName("title")
        private val titleB: String,
        @SerializedName("url")
        private val urlB: String,
        @SerializedName("imageUrl")
        private val imageUrlB: String,
        @SerializedName("newsSite")
        private val newsSiteB: String,
        @SerializedName("summary")
        private val summaryB: String,
        @SerializedName("publishedAt")
        private val publishedAtB: String,
        @SerializedName("updatedAt")
        private val updatedAtB: String,
    ) : BlogCloud {
        override fun <T> map(mapper: ToBlogMapper<T>) =
            mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB)
    }
}