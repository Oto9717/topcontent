package com.siradze.movies.di

import android.content.Context
import com.siradze.movies.util.StringHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object MoviesModule {

    @Singleton
    @Provides
    fun providesStringHelper(@ApplicationContext appContext: Context) : StringHelper{
        return StringHelper(appContext)
    }
}