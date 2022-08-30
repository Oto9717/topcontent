package com.siradze.movies.data.model

data class Movie (
    val id: Int,
    val name: String,
    val overview: String,
    val voteAverage: Double,
    val posterPath: String,
)