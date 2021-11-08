package com.example.spaceflightapp.data.blogs

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.DbWrapper
import com.example.spaceflightapp.data.blogs.cache.BlogDataToDbMapper
import io.realm.RealmObject

interface BlogData : Abstract.DataObject {
    fun <T> map(mapper: BlogDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: BlogDataToDbMapper<T>, db: DbWrapper<T>): T

    class Base(
        private val idB: Int,
        private val titleB: String,
        private val urlB: String,
        private val imageUrlB: String,
        private val newsSiteB: String,
        private val summaryB: String,
        private val publishedAtB: String,
        private val updatedAtB: String
    ) : BlogData {
        override fun <T> map(mapper: BlogDataToDomainMapper<T>) =
            mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB)

        override fun <T : RealmObject> map(mapper: BlogDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(
                idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB, db
            )
    }
}