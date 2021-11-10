package com.example.spaceflightapp.data.reports.cache

import com.example.spaceflightapp.core.*
import com.example.spaceflightapp.data.reports.ReportData
import io.realm.Realm

interface ReportCacheDataSource : CacheDataSource<ReportData>, Read<List<ReportDb>>,
    Update<List<ReportData>> {
    class Base(
        private val realmProvider: RealmProvider,
        private val mapper: ReportDataToDbMapper<ReportDb>
    ) : ReportCacheDataSource {
        override fun read(): List<ReportDb> {
            realmProvider.provide().use { realm ->
                val reportsDb = realm.where(ReportDb::class.java).findAll() ?: emptyList()
                return realm.copyFromRealm(reportsDb)
            }
        }

        override fun save(data: List<ReportData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                data.forEach { report ->
                    report.map(mapper, ReportDbWrapper(it))
                }
            }
        }

        override fun update(data: List<ReportData>) = realmProvider.provide().use { realm ->
            realm.executeTransaction {
                it.deleteAll()
                data.forEach { report ->
                    report.map(mapper, ReportDbWrapper(it))
                }
            }
        }

        private inner class ReportDbWrapper(realm: Realm) : DbWrapper.Base<ReportDb>(realm) {
            override fun dbClass() = ReportDb::class.java
        }
    }
}

