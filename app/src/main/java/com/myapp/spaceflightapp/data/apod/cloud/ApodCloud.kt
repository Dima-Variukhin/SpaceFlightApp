package com.myapp.spaceflightapp.data.apod.cloud

import com.google.gson.annotations.SerializedName
import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.data.apod.ToApodMapper

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