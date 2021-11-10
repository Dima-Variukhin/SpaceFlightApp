package com.example.spaceflightapp.domain.blogs

import com.example.spaceflightapp.core.ErrorType

sealed class BlogsDomain {
    abstract fun <T> map(mapper: BlogsDomainToUiMapper<T>): T

    data class Success(private val blogs: List<BlogDomain>) : BlogsDomain() {
        override fun <T> map(mapper: BlogsDomainToUiMapper<T>) = mapper.map(blogs)
    }

    data class Fail(private val errorType: ErrorType) : BlogsDomain() {
        override fun <T> map(mapper: BlogsDomainToUiMapper<T>) = mapper.map(errorType)
    }
}