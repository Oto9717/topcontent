package com.siradze.movies.di

import android.content.Context

import com.siradze.movies.data.api.ApiStrings
import com.siradze.movies.data.api.interceptors.ApiKeyInterceptor
import com.siradze.movies.data.api.interceptors.ErrorResponseInterceptor
import com.siradze.movies.data.api.interceptors.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesNetworkModule {

    @Singleton
    @Provides
    @Named("base_url")
    fun provideBaseUrl() : String{
        return ApiStrings.base_url
    }

    @Singleton
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