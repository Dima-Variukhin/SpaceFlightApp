package com.example.spaceflightapp.presentation.articles

import com.example.spaceflightapp.core.*

sealed class ArticleUi : FromUi<ArticleUi>, Match<Int>,
    Open {
    override fun matches(arg: Int) = false
    override fun open(show: Show) = Unit
    override fun share(show: Show) = Unit
    override fun map(mapper: AdapterNewsMapper<Unit>) = Unit
    override fun changeFavorite(show: Show) = Unit

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
        private val updatedAtA: String,
        private val data: String
    ) : ArticleUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) =
            mapper.map(
                idA,
                titleA,
                urlA,
                imageUrlA,
                newsSiteA,
                summaryA,
                publishedAtA,
                updatedAtA,
                data
            )

        override fun matches(arg: Int) = arg == idA
        override fun same(item: ArticleUi) = item is Base && idA == item.idA
        override fun sameContent(item: ArticleUi) = if (item is Base) {
            titleA == item.titleA
        } else false

        override fun open(show: Show) = show.open(urlA)
        override fun share(show: Show) = show.share(urlA)
        override fun changeFavorite(show: Show) = show.changeFavorite(
            idA,
            titleA,
            urlA,
            imageUrlA,
            newsSiteA,
            summaryA,
            publishedAtA,
            updatedAtA
        )
    }

    class Fail(private val message: String) : ArticleUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) = mapper.map(message)

        override fun sameContent(item: ArticleUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: ArticleUi) = sameContent(item)
    }
}

