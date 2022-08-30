package com.siradze.movies.movieDetails.data.model

data class MovieDetails(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val vote_average: Double,
    val vote_count: Int
)