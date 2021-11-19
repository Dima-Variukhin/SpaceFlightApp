package com.example.spaceflightapp.core

interface Show {
    fun open(data: String)
    fun share(data: String)
    fun changeFavorite(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
    )
}