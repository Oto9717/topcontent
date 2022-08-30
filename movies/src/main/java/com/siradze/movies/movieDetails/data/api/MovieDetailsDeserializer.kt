package com.siradze.movies.movieDetails.data.api

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.movieDetails.data.model.dto.MovieDetailsDto
import java.lang.reflect.Type

class MovieDetailsDeserializer : JsonDeserializer<MovieDetailsResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MovieDetailsResponse {
        val jsonObject = json?.asJsonObject
        jsonObject?.let{
            val detailsDto = Gson().fromJson(it, MovieDetailsDto::class.java)
            return MovieDetailsResponse.Success(detailsDto)
        }
        return MovieDetailsResponse.Error(ErrorResponseException(403, arrayListOf("invalid format")))
    }
}