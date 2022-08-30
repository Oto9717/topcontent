package com.siradze.movies.movieDetails.data.model.dto

import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.movieDetails.data.model.MovieDetails

data class MovieDetailsDto(
    val adult: Boolean?,
    val backdrop_path: String,
    val first_air_date: String?,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,
    val in_production: Boolean?,
    val last_air_date: String?,
    val name: String?,
    val title: String?,
    val networks: List<Network>?,
    val number_of_episodes: Int?,
    val number_of_seasons: Int?,
    val original_language: String,
    val original_name: String?,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val production_companies: List<ProductionCompany>?,
    val seasons: List<Season>?,
    val status: String?,
    val tagline: String?,
    val type: String?,
    val vote_average: Double,
    val vote_count: Int
){
    fun toMovieDetail() : MovieDetails {
        return MovieDetails(id, name ?: title ?: "", overview,
            ApiStrings.original_image_url + poster_path,
            ApiStrings.original_image_url + backdrop_path,
            vote_average, vote_count)
    }
}