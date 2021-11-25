package com.myapp.spaceflightapp.data.blogs.cache

import com.myapp.spaceflightapp.core.*
import com.myapp.spaceflightapp.data.blogs.BlogData
import io.realm.Realm

interface BlogsCacheDataSource : CacheDataSource<BlogData>, Read<List<BlogDb>>,
    Delete {
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

        override fun deleteAll() = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                it.delete(BlogDb::class.java)
            }
        }

        private inner class BlogDbWrapper(realm: Realm) : DbWrapper.Base<BlogDb>(realm) {
            override fun dbClass() = BlogDb::class.java
        }
    }
}
