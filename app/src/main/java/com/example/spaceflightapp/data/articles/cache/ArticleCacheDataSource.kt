package com.example.spaceflightapp.data.articles.cache

import com.example.spaceflightapp.core.CacheDataSource
import com.example.spaceflightapp.core.DbWrapper
import com.example.spaceflightapp.core.Read
import com.example.spaceflightapp.core.RealmProvider
import com.example.spaceflightapp.data.articles.ArticleData
import io.realm.Realm

interface ArticleCacheDataSource : CacheDataSource<ArticleData>, Read<List<ArticleDb>> {
    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ArticleDataToDbMapper<ArticleDb>
    ) : ArticleCacheDataSource {
        override fun read(): List<ArticleDb> {
            realmProvider.provide().use { realm ->
                val articlesDb = realm.where(ArticleDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(articlesDb)
            }
        }

        override fun save(data: List<ArticleData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                data.forEach { article ->
                    article.map(mapper, ArticleDbWrapper(it))
                }
            }
        }

        private inner class ArticleDbWrapper(realm: Realm) : DbWrapper.Base<ArticleDb>(realm) {
            override fun dbClass() = ArticleDb::class.java
        }
    }
}