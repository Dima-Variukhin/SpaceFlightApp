package com.example.spaceflightapp.data.articles.cache

import com.example.spaceflightapp.data.articles.ToArticleMapper
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class ArticleDb : RealmObject(), ArticleRealm {
    @PrimaryKey
    var idA: Int = -1
    var titleA: String = ""
    var urlA: String = ""
    var imageUrlA: String = ""
    var newsSiteA: String = ""
    var summaryA: String = ""
    var publishedAtA: String = ""
    var updatedAtA: String = ""

    override fun <T> map(mapper: ToArticleMapper<T>) =
        mapper.map(idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA)
}

interface ArticleRealm {
    fun <T> map(mapper: ToArticleMapper<T>): T
}