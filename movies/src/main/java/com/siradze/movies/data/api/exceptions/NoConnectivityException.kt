package com.siradze.movies.data.api.exceptions

import java.io.IOException

class NoConnectivityException : IOException() {
    override val message: String
        get() = "No internet Connection"
}