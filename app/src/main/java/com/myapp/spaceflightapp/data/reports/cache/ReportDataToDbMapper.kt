package com.myapp.spaceflightapp.data.reports.cache

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.DbWrapper
import io.realm.RealmObject

interface ReportDataToDbMapper<E : RealmObject> : Abstract.Mapper {
    fun mapToDb(
        idR: Int,
        titleR: String,
        urlR: String,
        imageUrlR: String,
        newsSiteR: String,
        summaryR: String,
        publishedAtR: String,
        updatedAtR: String,
        db: DbWrapper<E>
    ): E

    class Base : ReportDataToDbMapper<ReportDb> {
        override fun mapToDb(
            idR: Int,
            titleR: String,
            urlR: String,
            imageUrlR: String,
            newsSiteR: String,
            summaryR: String,
            publishedAtR: String,
            updatedAtR: String,
            db: DbWrapper<ReportDb>
        ) = db.createObject(idR).apply {
            this.titleR = titleR
            this.urlR = urlR
            this.imageUrlR = imageUrlR
            this.newsSiteR = newsSiteR
            this.summaryR = summaryR
            this.publishedAtR = publishedAtR
            this.updatedAtR = updatedAtR
        }
    }
}