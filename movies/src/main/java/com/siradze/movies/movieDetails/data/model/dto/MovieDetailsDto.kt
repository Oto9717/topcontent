package com.siradze.movies.movieDetails.data.model.dto

import com.google.gson.annotations.SerializedName
import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.movieDetails.domain.model.MovieDetails

data class MovieDetailsDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String?,
    @SerializedName("homepage")
    val homepage: String?,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("vote_count")
    val voteCount: Int
){
    fun toMovieDetail() : MovieDetails {
        return MovieDetails(id, name ?: title ?: "", overview,
            ApiStrings.original_image_url + posterPath,
            ApiStrings.original_image_url + backdropPath,
            voteAverage, voteCount)
    }
}