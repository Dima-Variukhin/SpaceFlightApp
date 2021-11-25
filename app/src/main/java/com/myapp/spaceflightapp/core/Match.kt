package com.myapp.spaceflightapp.core

interface Match<T> {
    fun matches(arg: T): Boolean
}