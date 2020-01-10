package com.example.moviedbkotlin.network

import com.example.moviedbkotlin.model.MovieResult
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class MovieRetrofit {
    val BASE_URL = "https://api.themoviedb.org"
    val API_KEY = "4f775d59b627d6e89557b34f030412bd"

    private lateinit var movieService: MovieService

    init {
        movieService = createService(retrofitInstance())
    }

    private fun retrofitInstance():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createService(retrofit:Retrofit):MovieService{
        return retrofit.create(MovieService::class.java)
    }

    fun getMovieResult(query:String):Observable<MovieResult>{
        return movieService.getMovies(API_KEY, query)
    }
}