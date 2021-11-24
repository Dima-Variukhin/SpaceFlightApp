package com.example.spaceflightapp.core

interface RepositoryContainer<T> {
    fun repository(): T
}