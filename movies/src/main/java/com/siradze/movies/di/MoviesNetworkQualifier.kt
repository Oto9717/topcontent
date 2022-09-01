package com.siradze.movies.di

import javax.inject.Qualifier


@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesNetworkBaseUrl

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class MoviesNetworkOkHttpClient