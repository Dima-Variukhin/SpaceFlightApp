package com.example.spaceflightapp.data.blogs

import com.example.spaceflightapp.core.Abstract

interface ToBlogMapper<T> : Abstract.Mapper {
    fun map(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String
    ): T

    class Base :
        ToBlogMapper<BlogData> {
        override fun map(
            idB: Int,
            titleB: String,
            urlB: String,
            imageUrlB: String,
            newsSiteB: String,
            summaryB: String,
            publishedAtB: String,
            updatedAtB: String
        ) = BlogData.Base(
            idB,
            titleB,
            urlB,
            imageUrlB,
            newsSiteB,
            summaryB,
            publishedAtB,
            updatedAtB
        )
    }
}