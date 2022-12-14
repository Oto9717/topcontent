package com.siradze.movies.util

internal sealed class Response<T> {
    class Success<T>(val data : T) : Response<T>()
    class Error<T>(val message: String, val reason: Reason = Reason.Other) : Response<T>()
}

internal enum class Reason{
    NoConnection, Other
}