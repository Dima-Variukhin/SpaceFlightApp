package com.example.spaceflightapp.data.blogs.cache

import com.example.spaceflightapp.data.blogs.ToBlogMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class BlogDb : RealmObject(), BlogRealm {
    @PrimaryKey
    var idB: Int = -1
    var titleB: String = ""
    var urlB: String = ""
    var imageUrlB: String = ""
    var newsSiteB: String = ""
    var summaryB: String = ""
    var publishedAtB: String = ""
    var updatedAtB: String = ""

    override fun <T> map(mapper: ToBlogMapper<T>) =
        mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB)
}

interface BlogRealm {
    fun <T> map(mapper: ToBlogMapper<T>): T
}