package com.example.spaceflightapp.core

interface Match<T> {
    fun matches(arg: T): Boolean
}