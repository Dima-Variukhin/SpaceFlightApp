package com.example.spaceflightapp.domain.reports

import java.text.SimpleDateFormat
import java.util.*

sealed class ReportDomain {
    abstract fun <T> map(mapper: ReportDomainToUiMapper<T>): T

    data class Base(
        private val idR: Int,
        private val titleR: String,
        private val urlR: String,
        private val imageUrlR: String,
        private val newsSiteR: String,
        private val summaryR: String,
        private val publishedAtR: String,
        private val updatedAtR: String
    ) : ReportDomain() {
        private val sdf = SimpleDateFormat("dd/M hh:mm:ss", Locale.ENGLISH)
        private val currentDate = sdf.format(Date())
        override fun <T> map(mapper: ReportDomainToUiMapper<T>) =
            mapper.map(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR, currentDate)
    }
}