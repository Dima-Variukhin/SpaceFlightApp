package com.example.spaceflightapp.core

interface Save<T> {
    fun save(data: T)
}

interface Update<T> {
    fun update(data: T)
}


