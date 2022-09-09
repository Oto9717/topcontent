package com.siradze.movies.moviesList.data.dataSource

import com.siradze.movies.util.Reason
import com.siradze.movies.util.Response
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Test

internal class MoviesListDataSourceImpTest {

    @Test
    fun getPopularTestSuccess(){
        val moviesListDataSourceImp = MoviesListDataSourceImp(FakeMoviesListApi.Success)
        runBlocking {
            val response = moviesListDataSourceImp.getPopular("", 1)
            assertTrue(response is Response.Success)
        }
    }

    @Test
    fun getPopularTestErrorNoConnection(){
        val moviesListDataSourceImp = MoviesListDataSourceImp(FakeMoviesListApi.ErrorNoConnection)
            runBlocking {
                val response = moviesListDataSourceImp.getPopular("", 1)
                assertTrue(response is Response.Error)
                if(response is Response.Error){
                    assertTrue( response.reason == Reason.NoConnection)
                }
            }
    }

    @Test
    fun getPopularTestError(){
        val moviesListDataSourceImp = MoviesListDataSourceImp(FakeMoviesListApi.Error)
        runBlocking {
            val response = moviesListDataSourceImp.getPopular("", 1)
            assertTrue(response is Response.Error)
            if(response is Response.Error){
                assertTrue(response.reason != Reason.NoConnection)
            }
        }
    }




}