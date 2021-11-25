package com.myapp.spaceflightapp.core

interface RepositoryContainer<T> {
    fun repository(): T
}