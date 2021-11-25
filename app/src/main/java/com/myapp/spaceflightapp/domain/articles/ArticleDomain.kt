package com.myapp.spaceflightapp.domain.articles

import java.text.SimpleDateFormat
import java.util.*

sealed class ArticleDomain {
    abstract fun <T> map(mapper: ArticleDomainToUiMapper<T>): T

    data class Base(
        private val idA: Int,
        private val titleA: String,
        private val urlA: String,
        private val imageUrlA: String,
        private val newsSiteA: String,
        private val summaryA: String,
        private val publishedAtA: String,
        private val updatedAtA: String
    ) : ArticleDomain() {
        private val sdf = SimpleDateFormat("dd/M hh:mm:ss", Locale.ENGLISH)
        private val currentDate = sdf.format(Date())
        override fun <T> map(mapper: ArticleDomainToUiMapper<T>) =
            mapper.map(
                idA,
                titleA,
                urlA,
                imageUrlA,
                newsSiteA,
                summaryA,
                publishedAtA,
                updatedAtA,
                currentDate
            )
    }
}