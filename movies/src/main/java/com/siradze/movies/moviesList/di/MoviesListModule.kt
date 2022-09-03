package com.siradze.movies.moviesList.di

import com.google.gson.GsonBuilder
import com.siradze.movies.di.MoviesNetworkBaseUrl
import com.siradze.movies.di.MoviesNetworkOkHttpClient
import com.siradze.movies.moviesList.domain.repository.MoviesListRepository
import com.siradze.movies.moviesList.data.MoviesListRepositoryImp
import com.siradze.movies.moviesList.data.api.MoviesListApi
import com.siradze.movies.moviesList.data.api.MoviesListDeserializer
import com.siradze.movies.moviesList.data.api.MoviesListResponse
import com.siradze.movies.moviesList.data.dataSource.MoviesListDataSource
import com.siradze.movies.moviesList.data.dataSource.MoviesListDataSourceImp
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
internal object MoviesListModule {
    @Singleton
    @Provides
    fun providesMoviesRepository(dataSource: MoviesListDataSource) : MoviesListRepository {
        return MoviesListRepositoryImp(dataSource)
    }
    @Singleton
    @Provides
    fun providesMoviesDataSource(api: MoviesListApi) : MoviesListDataSource {
        return MoviesListDataSourceImp(api)
    }
    @Singleton
    @Provides
    fun provideMoviesApi(@MoviesListRetrofit retrofit: Retrofit) : MoviesListApi {
        return retrofit.create(MoviesListApi::class.java)
    }

    @Singleton
    @Provides
    @MoviesListRetrofit
    fun providesRetrofit(
        @MoviesNetworkBaseUrl baseUrl: String,
        @MoviesNetworkOkHttpClient okHttpClient: OkHttpClient): Retrofit {

        val customGson = GsonBuilder()
            .registerTypeAdapter(MoviesListResponse::class.java, MoviesListDeserializer())
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(customGson))
            .build()
    }
}