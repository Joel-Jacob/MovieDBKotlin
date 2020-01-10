package com.example.moviedbkotlin.network

import com.example.moviedbkotlin.model.MovieResult
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/3/search/movie")
    fun getMovies(
        @Query ("api_key") apiKey: String,
        @Query("query") query: String )
            :Observable<MovieResult>
}