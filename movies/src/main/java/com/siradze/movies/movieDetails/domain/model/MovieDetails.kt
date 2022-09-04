package com.siradze.movies.movieDetails.domain.model

data class MovieDetails(
    val id: Int,
    val name: String,
    val overview: String,
    val posterPath: String,
    val backdropPath: String,
    val voteAverage: Double,
    val voteCount: Int
)