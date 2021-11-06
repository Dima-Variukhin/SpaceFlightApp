package com.example.spaceflightapp.data.articles.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.DbWrapper
import io.realm.RealmObject

interface ArticleDataToDbMapper<E : RealmObject> : Abstract.Mapper {
    fun mapToDb(
        idA: Int,
        titleA: String,
        urlA: String,
        imageUrlA: String,
        newsSiteA: String,
        summaryA: String,
        publishedAtA: String,
        updatedAtA: String,
        db: DbWrapper<E>
    ): E

    class Base : ArticleDataToDbMapper<ArticleDb> {
        override fun mapToDb(
            idA: Int,
            titleA: String,
            urlA: String,
            imageUrlA: String,
            newsSiteA: String,
            summaryA: String,
            publishedAtA: String,
            updatedAtA: String,
            db: DbWrapper<ArticleDb>
        ) = db.createObject(idA).apply {
            this.titleA = titleA
            this.urlA = urlA
            this.imageUrlA = imageUrlA
            this.newsSiteA = newsSiteA
            this.summaryA = summaryA
            this.publishedAtA = publishedAtA
            this.updatedAtA = updatedAtA
        }
    }
}