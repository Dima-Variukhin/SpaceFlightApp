package com.example.spaceflightapp.presentation.blogs

import com.example.spaceflightapp.core.ListMapper

sealed class BlogsUi {
    abstract fun map(mapper: ListMapper<BlogUi>)

    data class Base(private val blogs: MutableList<BlogUi>) : BlogsUi() {
        override fun map(mapper: ListMapper<BlogUi>) = mapper.map(blogs)
    }
}