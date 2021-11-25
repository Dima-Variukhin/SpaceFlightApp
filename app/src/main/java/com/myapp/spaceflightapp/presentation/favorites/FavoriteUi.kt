package com.myapp.spaceflightapp.presentation.favorites

import com.myapp.spaceflightapp.core.*

sealed class FavoriteUi : FromUi<FavoriteUi>, Match<Int>,
    Open {
    override fun matches(arg: Int) = false
    override fun open(show: Show) = Unit
    override fun share(show: Show) = Unit
    override fun map(mapper: AdapterNewsMapper<Unit>) = Unit
    override fun changeFavorite(show: Show) = Unit

    object Progress : FavoriteUi()

    class Base(
        private val id: Int,
        private val title: String,
        private val url: String,
        private val imageUrl: String,
        private val newsSite: String,
        private val summary: String,
        private val publishedAt: String,
        private val updatedAt: String,
        private val data: String
    ) : FavoriteUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) =
            mapper.map(
                id,
                title,
                url,
                imageUrl,
                newsSite,
                summary,
                publishedAt,
                updatedAt,
                data
            )

        override fun matches(arg: Int) = arg == id
        override fun same(item: FavoriteUi) = item is Base && id== item.id
        override fun sameContent(item: FavoriteUi) = if (item is Base) {
            title == item.title
        } else false

        override fun open(show: Show) = show.open(url)
        override fun share(show: Show) = show.share(url)
        override fun changeFavorite(show: Show) = show.changeFavorite(
            id,
            title,
            url,
            imageUrl,
            newsSite,
            summary,
            publishedAt,
            updatedAt
        )
    }

    class Fail(private val message: String) : FavoriteUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) = mapper.map(message)

        override fun sameContent(item: FavoriteUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: FavoriteUi) = sameContent(item)
    }
}