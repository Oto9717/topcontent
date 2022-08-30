package com.siradze.movies.movieDetails.data.api

import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.siradze.movies.data.api.exceptions.ErrorResponseException
import com.siradze.movies.data.model.dto.MovieDto
import java.lang.reflect.Type

class SimilarMoviesDeserializer : JsonDeserializer<SimilarMoviesResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): SimilarMoviesResponse {
        val jsonObject = json?.asJsonObject
        jsonObject?.let{
            if(jsonObject.has("results")){
                val moviesList : ArrayList<MovieDto> = arrayListOf()
                val dataJson = jsonObject["results"].asJsonArray
                dataJson.forEach {
                    try {
                        val movieDto = Gson().fromJson(it, MovieDto::class.java)
                        moviesList.add(movieDto)
                    }catch (e : Exception){
                        Log.i("myapp", "MoviesDeserializer deserialize error: ${e.message}")
                    }
                }
                return SimilarMoviesResponse.Success(moviesList)
            }
        }
        return SimilarMoviesResponse.Error(ErrorResponseException(403, arrayListOf("invalid format")))
    }
}