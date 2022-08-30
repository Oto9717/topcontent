package com.siradze.movies.data.api.exceptions

import java.io.IOException

data class ErrorResponseException(
    val code : Int,
    private val errors : List<String>
) : IOException(){
    override val message: String
        get() {
            var message = ""
            errors.forEach { message += "$it\n" }
            return message
        }
}