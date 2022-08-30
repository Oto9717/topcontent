package com.siradze.movies.di

import com.google.gson.GsonBuilder
import com.siradze.movies.movieDetails.data.api.MovieDetailsDeserializer
import com.siradze.movies.movieDetails.data.api.MovieDetailsResponse
import com.siradze.movies.movieDetails.data.api.SimilarMoviesDeserializer
import com.siradze.movies.movieDetails.data.api.SimilarMoviesResponse
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

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule {
    @Provides
    fun providesMoviesRepository(dataSource: MoviesDataSource) : MoviesRepository {
        return MoviesRepositoryImp(dataSource)
    }
    @Provides
    fun providesMoviesDataSource(api: MoviesApi) : MoviesDataSource {
        return MoviesDataSourceImp(api)
    }

    @Provides
    fun provideMoviesApi(@Named("movies_retrofit") retrofit: Retrofit) : MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }


    @Provides
    @Named("movies_retrofit")
    fun providesRetrofit(@Named("base_url") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
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