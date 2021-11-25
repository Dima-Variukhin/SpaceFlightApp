package com.myapp.spaceflightapp.core

import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDb
import io.realm.Realm

interface Save<T> {
    fun save(data: T)
}

interface Delete {
    fun deleteAll()
}

interface Favorite {
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

    fun findRealmObject(realm: Realm, id: Int) : FavoriteDb?
}


