package com.example.spaceflightapp.data.articles

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.DbWrapper
import com.example.spaceflightapp.data.articles.cache.ArticleDataToDbMapper
import io.realm.RealmObject

interface ArticleData : Abstract.DataObject {
    fun <T> map(mapper: ArticleDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: ArticleDataToDbMapper<T>, db: DbWrapper<T>): T

    class Base(
        private val idA: Int,
        private val titleA: String,
        private val urlA: String,
        private val imageUrlA: String,
        private val newsSiteA: String,
        private val summaryA: String,
        private val publishedAtA: String,
        private val updatedAtA: String
    ) : ArticleData {
        override fun <T> map(mapper: ArticleDataToDomainMapper<T>) =
            mapper.map(idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA)

        override fun <T : RealmObject> map(mapper: ArticleDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(
                idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA, db
            )
    }
}