package com.myapp.spaceflightapp.core

import com.myapp.spaceflightapp.R
import retrofit2.HttpException
import java.lang.Exception
import java.net.UnknownHostException

abstract class Abstract {
    interface Object<T, M : Mapper> {
        fun map(mapper: M): T
    }

    interface DataObject

    interface CloudObject

    interface Mapper {

        interface Data<S, R> : Mapper {
            fun map(data: S): R
        }

        interface DataToDomain<S, R> : Data<S, R> {
            fun map(e: Exception): R

            abstract class Base<S, R> : DataToDomain<S, R> {
                protected fun errorType(e: Exception) = when (e) {
                    is UnknownHostException -> ErrorType.NO_CONNECTION
                    is HttpException -> ErrorType.SERVICE_UNAVAILABLE
                    else -> ErrorType.GENERIC_ERROR
                }
            }
        }

        interface DomainToUi<S, T> : Data<S, T> {
            fun map(errorType: ErrorType): T

            abstract class Base<S, T>(private val resourceProvider: ResourceProvider) :
                DomainToUi<S, T> {

                protected fun errorMessage(errorType: ErrorType) = resourceProvider.string(
                    when (errorType) {
                        ErrorType.NO_CONNECTION -> R.string.no_connection_message
                        ErrorType.SERVICE_UNAVAILABLE -> R.string.service_unavailable_message
                        else -> R.string.something_went_wrong
                    }
                )
            }
        }

        class Empty : Mapper
    }
}

class NetworkConnectionException(cause: Throwable? = null) : Exception(cause)
class ServerUnavailableException : java.lang.Exception()