package com.example.moviedbkotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.moviedbkotlin.model.MovieResult
import com.example.moviedbkotlin.network.MovieRetrofit
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MovieViewModel(application:Application): AndroidViewModel(application){
    val movieRetrofit = MovieRetrofit()

    fun getMovies(query:String):Observable<MovieResult>{
        return movieRetrofit
            .getMovieResult(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}