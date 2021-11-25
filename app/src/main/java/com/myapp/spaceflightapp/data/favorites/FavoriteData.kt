package com.myapp.spaceflightapp.data.favorites


import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.DbWrapper
import com.myapp.spaceflightapp.data.favorites.cache.FavoriteDataToDbMapper
import io.realm.RealmObject

interface FavoriteData : Abstract.DataObject {
    fun <T> map(mapper: FavoriteDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: FavoriteDataToDbMapper<T>, db: DbWrapper<T>): T

    class Base(
        private val id: Int,
        private val title: String,
        private val url: String,
        private val imageUrl: String,
        private val newsSite: String,
        private val summary: String,
        private val publishedAt: String,
        private val updatedAt: String
    ) : FavoriteData {
        override fun <T> map(mapper: FavoriteDataToDomainMapper<T>) =
            mapper.map(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt)

        override fun <T : RealmObject> map(mapper: FavoriteDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(
                id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt, db
            )
    }
}