package com.siradze.movies.movieDetails.data.model.dto

data class ProductionCompany(
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String,
    val vote_average: Double,
    val vote_count: Int
)