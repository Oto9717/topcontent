package com.siradze.movies.data.model.dto

import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.data.model.Movie

data class MovieDto(
    val backdrop_path: String,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val id: Int,
    val name: String?,
    val title: String?,
    val origin_country: List<String>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val vote_average: Double,
    val vote_count: Int
){
    fun toMovie() : Movie {
        return Movie(id, name ?: title ?: "", overview, vote_average, ApiStrings.preview_image_url + poster_path)
    }
}