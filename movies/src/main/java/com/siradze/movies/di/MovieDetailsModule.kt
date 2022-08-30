package com.siradze.movies.di

import com.google.gson.GsonBuilder
import com.siradze.movies.movieDetails.data.MovieDetailsRepository
import com.siradze.movies.movieDetails.data.MovieDetailsRepositoryImp
import com.siradze.movies.movieDetails.data.api.*
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSource
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSourceImp
import com.siradze.movies.movies.data.api.MoviesDeserializer
import com.siradze.movies.movies.data.api.MoviesResponse
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
object MovieDetailsModule {
    @Provides
    fun providesMovieDetailsRepository(dataSource: MovieDetailsDataSource) : MovieDetailsRepository {
        return MovieDetailsRepositoryImp(dataSource)
    }
    @Provides
    fun providesMovieDetailsDataSource(api: MovieDetailsApi) : MovieDetailsDataSource {
        return MovieDetailsDataSourceImp(api)
    }
    @Provides
    fun provideMovieDetailsApi(@Named("movieDetails_retrofit") retrofit: Retrofit) : MovieDetailsApi {
        return retrofit.create(MovieDetailsApi::class.java)
    }

    @Provides
    @Named("movieDetails_retrofit")
    fun providesRetrofit(@Named("base_url") baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        val customGson = GsonBuilder()
            .registerTypeAdapter(MovieDetailsResponse::class.java, MovieDetailsDeserializer())
            .registerTypeAdapter(SimilarMoviesResponse::class.java, SimilarMoviesDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .build()
    }
}