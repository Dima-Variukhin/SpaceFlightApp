package com.myapp.spaceflightapp.presentation.reports

import com.myapp.spaceflightapp.core.*

sealed class ReportUi : FromUi<ReportUi>, Match<Int>,
    Open {
    override fun matches(arg: Int) = false
    override fun open(show: Show) = Unit
    override fun share(show: Show) = Unit
    override fun map(mapper: AdapterNewsMapper<Unit>) = Unit
    override fun changeFavorite(show: Show) = Unit

    object Progress : ReportUi()

    class Base(
        private val idR: Int,
        private val titleR: String,
        private val urlR: String,
        private val imageUrlR: String,
        private val newsSiteR: String,
        private val summaryR: String,
        private val publishedAtR: String,
        private val updatedAtR: String,
        private val data: String
    ) : ReportUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) =
            mapper.map(
                idR,
                titleR,
                urlR,
                imageUrlR,
                newsSiteR,
                summaryR,
                publishedAtR,
                updatedAtR,
                data
            )

        override fun changeFavorite(show: Show) = show.changeFavorite(
            idR,
            titleR,
            urlR,
            imageUrlR,
            newsSiteR,
            summaryR,
            publishedAtR,
            updatedAtR
        )

        override fun share(show: Show) = show.share(urlR)

        override fun matches(arg: Int) = arg == idR
        override fun same(item: ReportUi) = item is Base && idR == item.idR
        override fun sameContent(item: ReportUi) = if (item is Base) {
            titleR == item.titleR
        } else false

        override fun open(show: Show) = show.open(urlR)
    }

    class Fail(private val message: String) : ReportUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) = mapper.map(message)

        override fun sameContent(item: ReportUi) = if (item is Fail) {
            message == item.message
        } else false

        override fun same(item: ReportUi) = sameContent(item)
    }
}