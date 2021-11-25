package com.example.spaceflightapp.domain.launches

import java.lang.NumberFormatException
import java.util.*

interface Validator<T> {
    fun isValid(data: T): Boolean?

    class Base : Validator<String?> {
        override fun isValid(data: String?): Boolean? {
            return if (data.isNullOrEmpty() || data.length < YEAR_LENGTH) {
                null
            } else {
                try {
                    val year = data.toInt()
                    year > MINIMUM_YEAR && year <=  Calendar.getInstance().get(Calendar.YEAR)
                } catch (e: NumberFormatException) {
                    false
                }
            }
        }

        companion object {
            const val MINIMUM_YEAR = 0
            const val YEAR_LENGTH = 4
        }
    }
}