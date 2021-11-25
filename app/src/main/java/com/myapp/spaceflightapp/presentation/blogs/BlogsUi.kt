package com.myapp.spaceflightapp.presentation.blogs

import com.myapp.spaceflightapp.core.ListMapper

sealed class BlogsUi {
    abstract fun map(mapper: ListMapper<BlogUi>)

    data class Base(private val blogs: MutableList<BlogUi>) : BlogsUi() {
        override fun map(mapper: ListMapper<BlogUi>) = mapper.map(blogs)
    }
}