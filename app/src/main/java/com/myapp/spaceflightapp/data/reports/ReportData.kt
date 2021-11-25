package com.myapp.spaceflightapp.data.reports

import com.myapp.spaceflightapp.core.Abstract
import com.myapp.spaceflightapp.core.DbWrapper
import com.myapp.spaceflightapp.data.reports.cache.ReportDataToDbMapper
import io.realm.RealmObject

interface ReportData : Abstract.DataObject {
    fun <T> map(mapper: ReportDataToDomainMapper<T>): T
    fun <T : RealmObject> map(mapper: ReportDataToDbMapper<T>, db: DbWrapper<T>): T

    class Base(
        private val idR: Int,
        private val titleR: String,
        private val urlR: String,
        private val imageUrlR: String,
        private val newsSiteR: String,
        private val summaryR: String,
        private val publishedAtR: String,
        private val updatedAtR: String
    ) : ReportData {

        override fun <T> map(mapper: ReportDataToDomainMapper<T>) =
            mapper.map(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR)

        override fun <T : RealmObject> map(mapper: ReportDataToDbMapper<T>, db: DbWrapper<T>) =
            mapper.mapToDb(
                idR,
                titleR,
                urlR,
                imageUrlR,
                newsSiteR,
                summaryR,
                publishedAtR,
                updatedAtR,
                db
            )
    }
}