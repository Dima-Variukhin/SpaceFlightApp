package com.example.spaceflightapp.presentation.blogs

import com.example.spaceflightapp.core.*

sealed class BlogUi : FromUi<BlogUi>, Match<Int>,
    Open {
    override fun matches(arg: Int) = false
    override fun open(show: Show) = Unit
    override fun map(mapper: AdapterNewsMapper<Unit>) = Unit

    object Empty : BlogUi()
    object Progress : BlogUi()

    class Base(
        private val idB: Int,
        private val titleB: String,
        private val urlB: String,
        private val imageUrlB: String,
        private val newsSiteB: String,
        private val summaryB: String,
        private val publishedAtB: String,
        private val updatedAtB: String
    ) : BlogUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) =
            mapper.map(idB, titleB, urlB, imageUrlB, newsSiteB, summaryB, publishedAtB, updatedAtB)

        override fun matches(arg: Int) = arg == idB
        override fun same(item: BlogUi) = item is Base && idB == item.idB
        override fun sameContent(item: BlogUi) = if (item is Base) {
            titleB == item.titleB
        } else false

        override fun open(show: Show) = show.open(urlB)
    }

    class Fail(private val message: String) : BlogUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) = mapper.map(message)

        override fun sameContent(item: BlogUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: BlogUi) = sameContent(item)
    }
}