package com.example.spaceflightapp.domain.articles

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
        override fun <T> map(mapper: ArticleDomainToUiMapper<T>) =
            mapper.map(idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA)
    }
}