package com.example.spaceflightapp.domain.apod

import com.example.spaceflightapp.data.apod.ApodCheckDataToDomainMapper
import com.example.spaceflightapp.data.apod.ApodRepository

interface ApodInteractor {
    suspend fun fetch(url: String): ApodCheckDomain
    suspend fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
    )

    class Base(
        private val apodRepository: ApodRepository,
        private val mapper: ApodCheckDataToDomainMapper<ApodCheckDomain>
    ) : ApodInteractor {
        override suspend fun fetch(url: String) = apodRepository.fetch(url).map(mapper)
        override suspend fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = apodRepository.changeFavorite(
            id,
            title,
            url,
            imageUrl,
            newsSite,
            summary,
            publishedAt,
            updatedAt
        )
    }
}