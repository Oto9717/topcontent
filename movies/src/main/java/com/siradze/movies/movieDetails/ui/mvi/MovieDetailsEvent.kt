package com.siradze.movies.movieDetails.ui.mvi

import com.siradze.movies.MoviesType

sealed class MovieDetailsEvent {
    data class Load(val type : MoviesType, val id : String) : MovieDetailsEvent()
}