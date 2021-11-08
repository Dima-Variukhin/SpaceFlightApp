package com.example.spaceflightapp.data.blogs.cache

import com.example.spaceflightapp.core.Abstract
import com.example.spaceflightapp.core.DbWrapper
import io.realm.RealmObject

interface BlogDataToDbMapper<E : RealmObject> : Abstract.Mapper {
    fun mapToDb(
        idB: Int,
        titleB: String,
        urlB: String,
        imageUrlB: String,
        newsSiteB: String,
        summaryB: String,
        publishedAtB: String,
        updatedAtB: String,
        db: DbWrapper<E>
    ): E

    class Base :
        BlogDataToDbMapper<BlogDb> {
        override fun mapToDb(
            idB: Int,
            titleB: String,
            urlB: String,
            imageUrlB: String,
            newsSiteB: String,
            summaryB: String,
            publishedAtB: String,
            updatedAtB: String,
            db: DbWrapper<BlogDb>
        ) = db.createObject(idB).apply {
            this.titleB = titleB
            this.urlB = urlB
            this.imageUrlB = imageUrlB
            this.newsSiteB = newsSiteB
            this.summaryB = summaryB
            this.publishedAtB = publishedAtB
            this.updatedAtB = updatedAtB
        }
    }
}