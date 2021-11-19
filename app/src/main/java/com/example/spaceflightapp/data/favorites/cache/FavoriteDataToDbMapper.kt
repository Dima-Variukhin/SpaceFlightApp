package com.example.spaceflightapp.data.favorites.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.DbWrapper
import io.realm.RealmObject

interface FavoriteDataToDbMapper<E : RealmObject> : Abstract.Mapper {
    fun mapToDb(
        id: Int,
        title: String,
        url: String,
        imageUrl: String,
        newsSite: String,
        summary: String,
        publishedAt: String,
        updatedAt: String,
        db: DbWrapper<E>
    ): E

    class Base :
        FavoriteDataToDbMapper<FavoriteDb> {
        override fun mapToDb(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String,
            db: DbWrapper<FavoriteDb>
        ) = db.createObject(id).apply {
            this.title = title
            this.url = url
            this.imageUrl = imageUrl
            this.newsSite = newsSite
            this.summary = summary
            this.publishedAt = publishedAt
            this.updatedAt = updatedAt
        }
    }
}