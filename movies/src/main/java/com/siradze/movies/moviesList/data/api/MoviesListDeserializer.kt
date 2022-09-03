package com.siradze.movies.moviesList.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.data.model.dto.MovieDto
import java.lang.reflect.Type

internal class MoviesListDeserializer : JsonDeserializer<MoviesListResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): MoviesListResponse {
        val jsonObject = json?.asJsonObject
        jsonObject?.let{
            if(jsonObject.has("results")){
                val totalPages : Int = jsonObject.get("total_pages").asInt
                val moviesList : ArrayList<MovieDto> = arrayListOf()
                val dataJson = jsonObject["results"].asJsonArray
                dataJson.forEach {
                    try {
                        val movieDto = Gson().fromJson(it, MovieDto::class.java)
                        moviesList.add(movieDto)
                    }catch (e : Exception){
                        Log.i("MoviesDeserializer", "MoviesDeserializer deserialize error: ${e.message}")
                    }
                }
                return MoviesListResponse.Success(moviesList, totalPages)
            }
        }
        return MoviesListResponse.Error(ErrorResponseException(403, arrayListOf("invalid format")))
    }
}