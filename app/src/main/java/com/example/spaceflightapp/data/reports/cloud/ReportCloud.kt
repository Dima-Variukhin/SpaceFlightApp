package com.example.spaceflightapp.data.reports.cloud

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.data.reports.ToReportMapper
import com.google.gson.annotations.SerializedName

interface ReportCloud : Abstract.CloudObject {
    fun <T> map(mapper: ToReportMapper<T>): T

    data class Base(
        @SerializedName("id")
        private val idR: Int,
        @SerializedName("title")
        private val titleR: String,
        @SerializedName("url")
        private val urlR: String,
        @SerializedName("imageUrl")
        private val imageUrlR: String,
        @SerializedName("newsSite")
        private val newsSiteR: String,
        @SerializedName("summary")
        private val summaryR: String,
        @SerializedName("publishedAt")
        private val publishedAtR: String,
        @SerializedName("updatedAt")
        private val updatedAtR: String,
    ) : ReportCloud {
        override fun <T> map(mapper: ToReportMapper<T>) =
            mapper.map(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR)
    }
}