package com.siradze.movies.moviesList.domain.model

import com.siradze.movies.domain.model.Movie

internal data class MoviesResponseData(
    val list: List<Movie>,
    val totalPages : Int,
)