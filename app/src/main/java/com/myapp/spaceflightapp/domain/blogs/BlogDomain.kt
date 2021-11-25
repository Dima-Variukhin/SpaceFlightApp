package com.myapp.spaceflightapp.domain.blogs

import java.text.SimpleDateFormat
import java.util.*

sealed class BlogDomain {
    abstract fun <T> map(mapper: BlogDomainToUiMapper<T>): T

    data class Base(
        private val idB: Int,
        private val titleB: String,
        private val urlB: String,
        private val imageUrlB: String,
        private val newsSiteB: String,
        private val summaryB: String,
        private val publishedAtB: String,
        private val updatedAtB: String
    ) : BlogDomain() {
        private val sdf = SimpleDateFormat("dd/M hh:mm:ss", Locale.ENGLISH)
        private val currentDate = sdf.format(Date())
        override fun <T> map(mapper: BlogDomainToUiMapper<T>) =
            mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB, currentDate)
    }
}