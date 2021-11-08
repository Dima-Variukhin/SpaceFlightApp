package com.example.spaceflightapp.data.blogs

import com.example.spaceflightapp.core.Abstract


sealed class BlogsData : Abstract.DataObject {
    abstract fun <T> map(mapper: BlogsDataToDomainMapper<T>): T

    data class Success(private val blogs: List<BlogData>) : BlogsData() {
        override fun <T> map(mapper: BlogsDataToDomainMapper<T>) = mapper.map(blogs)
    }

    data class Fail(private val e: Exception) : BlogsData() {
        override fun <T> map(mapper: BlogsDataToDomainMapper<T>) = mapper.map(e)
    }
}