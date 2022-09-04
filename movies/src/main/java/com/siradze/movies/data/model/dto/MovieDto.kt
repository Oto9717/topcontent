package com.siradze.movies.data.model.dto

import com.google.gson.annotations.SerializedName
import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.domain.model.Movie

internal data class MovieDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String?,
    @SerializedName("original_name")
    val original_name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("backdrop_path")
    val backdrop_path: String,
    @SerializedName("first_air_date")
    val first_air_date: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int
){
    fun toMovie() : Movie {
        return Movie(id, name ?: title ?: "", overview, vote_average, ApiStrings.preview_image_url + poster_path)
    }
}