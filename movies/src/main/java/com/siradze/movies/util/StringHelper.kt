package com.siradze.movies.util

import android.content.Context
import androidx.annotation.StringRes

class StringHelper(private val context: Context) {
    fun get(@StringRes id : Int) : String{
        val string = context?.resources?.getString(id)
        return string ?: id.toString()
    }
}