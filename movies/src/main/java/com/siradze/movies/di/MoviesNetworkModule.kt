package com.siradze.movies.di

import android.content.Context
import com.google.gson.GsonBuilder
import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.data.api.interceptors.ApiKeyInterceptor
import com.siradze.movies.data.api.interceptors.ErrorResponseInterceptor
import com.siradze.movies.data.api.interceptors.NetworkConnectionInterceptor
import com.siradze.movies.movieDetails.data.api.MovieDetailsDeserializer
import com.siradze.movies.movieDetails.data.api.MovieDetailsResponse
import com.siradze.movies.movieDetails.data.api.SimilarMoviesDeserializer
import com.siradze.movies.movieDetails.data.api.SimilarMoviesResponse
import com.siradze.movies.movies.data.api.MoviesDeserializer
import com.siradze.movies.movies.data.api.MoviesResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object MoviesNetworkModule {

    @Provides
    @Named("base_url")
    fun provideBaseUrl() : String{
        return ApiStrings.base_url
    }

    @Provides
    fun providesHttpClient(@ApplicationContext appContext: Context) : OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method(), original.body())
            val request = requestBuilder.build()
            chain.proceed(request)
        }
            .addInterceptor(NetworkConnectionInterceptor(appContext))
            .addInterceptor(ErrorResponseInterceptor())
            .addInterceptor(ApiKeyInterceptor(ApiStrings.api_key))
            .build()
    }


}