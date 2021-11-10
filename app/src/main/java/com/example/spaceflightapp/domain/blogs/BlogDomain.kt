package com.example.spaceflightapp.domain.blogs

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
        override fun <T> map(mapper: BlogDomainToUiMapper<T>) =
            mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB)
    }
}