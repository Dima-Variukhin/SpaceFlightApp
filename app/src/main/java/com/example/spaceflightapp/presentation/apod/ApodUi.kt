package com.example.spaceflightapp.presentation.apod

import com.example.spaceflightapp.core.*

sealed class ApodUi : FromUi<ApodUi>, Open {
    override fun map(mapper: AdapterNewsMapper<Unit>) = Unit
    override fun open(show: Show) = Unit
    override fun share(show: Show) = Unit
    override fun changeFavorite(show: Show) = Unit

    object Progress : ApodUi()

    class Base(
        private val date: String,
        private val explanation: String,
        private val url: String,
        private val copyright: String?,
        private val title: String,
    ) : ApodUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) =
            mapper.map(date.hashCode(), title, "", url, "", explanation, date, copyright!!, "")

        override fun share(show: Show) = show.share(url)

        override fun changeFavorite(show: Show) =
            show.changeFavorite(date.hashCode(), title, url, url, "", explanation, date, copyright!!)
    }

    class Fail(private val message: String) : ApodUi() {
        override fun map(mapper: AdapterNewsMapper<Unit>) = mapper.map(message)
    }
}