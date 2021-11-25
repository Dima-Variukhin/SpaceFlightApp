package com.myapp.spaceflightapp.domain.favorites

import java.text.SimpleDateFormat
import java.util.*

sealed class FavoriteDomain {
    abstract fun <T> map(mapper: FavoriteDomainToUiMapper<T>): T

    data class Base(
        private val id: Int,
        private val title: String,
        private val url: String,
        private val imageUrl: String,
        private val newsSite: String,
        private val summary: String,
        private val publishedAt: String,
        private val updatedAt: String
    ) : FavoriteDomain() {
        private val sdf = SimpleDateFormat("dd/M hh:mm:ss", Locale.ENGLISH)
        private val currentDate = sdf.format(Date())
        override fun <T> map(mapper: FavoriteDomainToUiMapper<T>) =
            mapper.map(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt,
                currentDate
            )
    }
}