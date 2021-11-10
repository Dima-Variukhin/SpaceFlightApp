package com.example.spaceflightapp.data.reports.cache

import com.example.spaceflightapp.data.reports.ToReportMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ReportDb : RealmObject(), ReportRealm {
    @PrimaryKey
    var idR: Int = -1
    var titleR: String = ""
    var urlR: String = ""
    var imageUrlR: String = ""
    var newsSiteR: String = ""
    var summaryR: String = ""
    var publishedAtR: String = ""
    var updatedAtR: String = ""
    override fun <T> map(mapper: ToReportMapper<T>) =
        mapper.map(idR, titleR, urlR, imageUrlR, newsSiteR, summaryR, publishedAtR, updatedAtR)
}

interface ReportRealm {
    fun <T> map(mapper: ToReportMapper<T>): T
}