package com.myapp.spaceflightapp.data.favorites.cache

import com.myapp.spaceflightapp.data.favorites.ToFavoriteMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class FavoriteDb : RealmObject(), FavoriteRealm {
    @PrimaryKey
    var id: Int = -1
    var title: String = ""
    var url: String = ""
    var imageUrl: String = ""
    var newsSite: String = ""
    var summary: String = ""
    var publishedAt: String = ""
    var updatedAt: String = ""
    override fun <T> map(mapper: ToFavoriteMapper<T>) =
        mapper.map(id, title, url, imageUrl, newsSite, summary, publishedAt, updatedAt)
}

interface FavoriteRealm {
    fun <T> map(mapper: ToFavoriteMapper<T>): T
}