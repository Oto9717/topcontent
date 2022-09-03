package com.siradze.movies.domain.model

internal data class Movie (
    val id: Int,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
)