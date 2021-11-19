package com.example.spaceflightapp.data.favorites.cache

import com.example.spaceflightapp.core.*
import com.example.spaceflightapp.data.favorites.FavoriteData
import io.realm.Realm

interface FavoriteCacheDataSource : Favorite, Read<List<FavoriteDb>>,
    CacheDataSource<FavoriteData> {
    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: FavoriteDataToDbMapper<FavoriteDb>,
    ) : FavoriteCacheDataSource {
        override fun findRealmObject(realm: Realm, id: Int): FavoriteDb? =
            realm.where(FavoriteDb::class.java).equalTo("id", id).findFirst()

        override fun changeFavorite(
            id: Int,
            title: String,
            url: String,
            imageUrl: String,
            newsSite: String,
            summary: String,
            publishedAt: String,
            updatedAt: String
        ) = realmProvider.provide().use { realm ->
            val itemRealm = findRealmObject(realm, id)
            if (itemRealm == null) {
                realm.executeTransaction {
                    val data = FavoriteData.Base(
                        id,
                        title,
                        url,
                        imageUrl,
                        newsSite,
                        summary,
                        publishedAt,
                        updatedAt
                    )
                    val item = data.map(mapper, FavoriteDbWrapper(it))
                    it.insert(item)
                }
            } else {
                realm.executeTransaction {
                    findRealmObject(realm, id)?.deleteFromRealm()
                }
            }
        }

        override fun read(): List<FavoriteDb> {
            realmProvider.provide().use { realm ->
                val favoritesDb = realm.where(FavoriteDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(favoritesDb)
            }
        }

        override fun save(data: List<FavoriteData>) = Unit
        override fun deleteAll() = Unit

        private inner class FavoriteDbWrapper(realm: Realm) : DbWrapper.Base<FavoriteDb>(realm) {
            override fun dbClass() = FavoriteDb::class.java
        }
    }
}