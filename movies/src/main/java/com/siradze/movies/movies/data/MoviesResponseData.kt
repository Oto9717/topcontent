package com.siradze.movies.movies.data

import com.siradze.movies.data.model.Movie

data class MoviesResponseData(
    val list: List<Movie>,
    val totalPages : Int,
)