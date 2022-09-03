package com.siradze.movies.movieDetails.di
import com.google.gson.GsonBuilder
import com.siradze.movies.di.MoviesNetworkBaseUrl
import com.siradze.movies.di.MoviesNetworkOkHttpClient
import com.siradze.movies.movieDetails.domain.repository.MovieDetailsRepository
import com.siradze.movies.movieDetails.data.repository.MovieDetailsRepositoryImp
import com.siradze.movies.movieDetails.data.api.*
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSource
import com.siradze.movies.movieDetails.data.dataSource.MovieDetailsDataSourceImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MovieDetailsModule {

    @Singleton
    @Provides
    fun providesMovieDetailsRepository(dataSource: MovieDetailsDataSource) : MovieDetailsRepository {
        return MovieDetailsRepositoryImp(dataSource)
    }
    @Singleton
    @Provides
    fun providesMovieDetailsDataSource(api: MovieDetailsApi) : MovieDetailsDataSource {
        return MovieDetailsDataSourceImp(api)
    }
    @Singleton
    @Provides
    fun provideMovieDetailsApi(@MovieDetailsRetrofit retrofit: Retrofit) : MovieDetailsApi {
       return retrofit.create(MovieDetailsApi::class.java)
    }

    @Singleton
    @Provides
    @MovieDetailsRetrofit
    fun providesRetrofit(
        @MoviesNetworkBaseUrl baseUrl: String,
        @MoviesNetworkOkHttpClient okHttpClient: OkHttpClient): Retrofit {
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