package com.example.spaceflightapp.core

interface RepositoryContainer<T : BaseRepository<*>> {
    fun repository(): T
}