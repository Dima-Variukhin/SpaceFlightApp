package com.example.spaceflightapp.domain.reports

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
        override fun <T> map(mapper: ReportDomainToUiMapper<T>) =
            mapper.map(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR)
    }
}