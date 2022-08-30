package com.siradze.movies.data.api.interceptors

import com.google.gson.Gson
import com.google.gson.JsonParser
import com.siradze.movies.data.api.exceptions.ErrorResponseException
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class ErrorResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val response = chain.proceed(builder.build())
        if (response.isSuccessful) {
            return response
        } else {
            val jsonObject = response.body()?.string()?.let { JsonParser().parse(it).asJsonObject; }
            jsonObject?.let{
                if(jsonObject.has("success") && !jsonObject["success"].asBoolean){
                    val message : String = try{ jsonObject.get("status_message").asString }catch (e : Exception){ ""}
                    throw ErrorResponseException(code = response.code(), errors = listOf(message))
                }

                if(jsonObject.has("errors")){
                    val errors : MutableList<String> = mutableListOf()
                    val dataJson = jsonObject["errors"].asJsonArray
                    dataJson.forEach {
                        val error = it.asString
                        errors.add(error)
                    }
                    throw ErrorResponseException(code = response.code(), errors)
                }
            }
        }
        return response
    }
}