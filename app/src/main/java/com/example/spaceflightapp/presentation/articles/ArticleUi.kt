package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.core.*

sealed class ArticleUi : FromUi<ArticleUi>, Match<Int>,
    Open {
    override fun matches(arg: Int) = false
    override fun open(show: Show) = Unit
    override fun map(mapper: AdapterArticleMapper<Unit>) = Unit

    object Empty : ArticleUi()
    object Progress : ArticleUi()

    class Base(
        private val idA: Int,
        private val titleA: String,
        private val urlA: String,
        private val imageUrlA: String,
        private val newsSiteA: String,
        private val summaryA: String,
        private val publishedAtA: String,
        private val updatedAtA: String
    ) : ArticleUi() {
        override fun map(mapper: AdapterArticleMapper<Unit>) =
            mapper.map(idA, titleA, urlA, imageUrlA, newsSiteA, summaryA, publishedAtA, updatedAtA)

        override fun matches(arg: Int) = arg == idA
        override fun same(item: ArticleUi) = item is Base && idA == item.idA
        override fun sameContent(item: ArticleUi) = if (item is Base) {
            titleA == item.titleA
        } else false
    }

    class Fail(private val message: String) : ArticleUi() {
        override fun map(mapper: AdapterArticleMapper<Unit>) = mapper.map(message)

        override fun sameContent(item: ArticleUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: ArticleUi) = sameContent(item)
    }
}

