package com.siradze.movies.movieDetails.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MovieDetailsApi {
    @GET("{type}/{id}")
    suspend fun getDetails(
        @Path(value = "type", encoded = true) type : String,
        @Path(value = "id", encoded = true) id : String,
    ) : MovieDetailsResponse

    @GET("{type}/{id}/similar")
    suspend fun getSimilarMovies(
        @Path(value = "type", encoded = true) type : String,
        @Path(value = "id", encoded = true) id : String,
        @Query(value = "page", encoded = true) page : Int,
    ) : SimilarMoviesResponse
}