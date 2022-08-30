package com.siradze.movies.movies.data.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MoviesApi {
    @GET("{type}/popular")
    suspend fun getPopular(
        @Path(value = "type", encoded = true) type : String,
        @Query(value = "page", encoded = true) page : Int,
    ) : MoviesResponse

    @GET("search/{type}")
    suspend fun search(
        @Path(value = "type", encoded = true) type : String,
        @Query(value = "query", encoded = true) query : String,
        @Query(value = "page", encoded = true) page : Int,
    ) : MoviesResponse
}