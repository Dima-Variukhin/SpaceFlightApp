package com.example.spaceflightapp.data.blogs.cache

import com.example.spaceflightapp.core.*
import com.example.spaceflightapp.data.blogs.BlogData
import io.realm.Realm

interface BlogsCacheDataSource : CacheDataSource<BlogData>, Read<List<BlogDb>>,
    Update<List<BlogData>> {
    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: BlogDataToDbMapper<BlogDb>
    ) : BlogsCacheDataSource {
        override fun read(): List<BlogDb> {
            realmProvider.provide().use { realm ->
                val blogsDb = realm.where(BlogDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(blogsDb)
            }
        }

        override fun save(data: List<BlogData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                data.forEach { blog ->
                    blog.map(mapper, BlogDbWrapper(it))
                }
            }
        }

        override fun update(data: List<BlogData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                it.deleteAll()
                data.forEach { blog ->
                    blog.map(mapper, BlogDbWrapper(it))
                }
            }
        }

        private inner class BlogDbWrapper(realm: Realm) : DbWrapper.Base<BlogDb>(realm) {
            override fun dbClass() = BlogDb::class.java
        }
    }
}
