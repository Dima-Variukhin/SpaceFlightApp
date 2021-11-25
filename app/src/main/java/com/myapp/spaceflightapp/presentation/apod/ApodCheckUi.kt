package com.myapp.spaceflightapp.presentation.apod

import com.myapp.spaceflightapp.core.ListMapper

sealed class ApodCheckUi {
    abstract fun map(mapper: ListMapper<ApodUi>)

    data class Base(private val apod: MutableList<ApodUi>) : ApodCheckUi() {
        override fun map(mapper: ListMapper<ApodUi>) = mapper.map(apod)
    }
}