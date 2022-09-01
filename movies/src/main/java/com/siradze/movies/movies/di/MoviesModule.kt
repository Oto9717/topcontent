package com.siradze.movies.movies.di

import com.google.gson.GsonBuilder
import com.siradze.movies.di.MoviesNetworkBaseUrl
import com.siradze.movies.di.MoviesNetworkOkHttpClient
import com.siradze.movies.movies.data.MoviesRepository
import com.siradze.movies.movies.data.MoviesRepositoryImp
import com.siradze.movies.movies.data.api.MoviesApi
import com.siradze.movies.movies.data.api.MoviesDeserializer
import com.siradze.movies.movies.data.api.MoviesResponse
import com.siradze.movies.movies.data.dataSource.MoviesDataSource
import com.siradze.movies.movies.data.dataSource.MoviesDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {
    @Singleton
    @Provides
    fun providesMoviesRepository(dataSource: MoviesDataSource) : MoviesRepository {
        return MoviesRepositoryImp(dataSource)
    }
    @Singleton
    @Provides
    fun providesMoviesDataSource(api: MoviesApi) : MoviesDataSource {
        return MoviesDataSourceImp(api)
    }
    @Singleton
    @Provides
    fun provideMoviesApi(@MoviesRetrofit retrofit: Retrofit) : MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    @MoviesRetrofit
    fun providesRetrofit(
        @MoviesNetworkBaseUrl baseUrl: String,
        @MoviesNetworkOkHttpClient okHttpClient: OkHttpClient): Retrofit {

        val customGson = GsonBuilder()
            .registerTypeAdapter(MoviesResponse::class.java, MoviesDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .build()
    }
}