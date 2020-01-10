package com.example.moviedbkotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.moviedbkotlin.R
import com.example.moviedbkotlin.model.Result
import kotlinx.android.synthetic.main.movie_fragment_layout.view.*

class ViewMovieFragment: Fragment() {

    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view : View = inflater.inflate(R.layout.movie_fragment_layout, container,false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Log.d("TAG_X", "HERE BOIS")

        var bundle = arguments
        var movie = bundle?.getParcelable<Result>("selected_movie")

        view.frag_title_tv.setText(movie?.title)
        view.frag_score_tv.setText("Score: " + movie?.voteAverage.toString())
        view.frag_date_tv.setText(movie?.releaseDate)
        view.frag_overview_tv.setText(movie?.overview)

        Glide.with(view.context.applicationContext)
            .load(IMAGE_URL + movie?.posterPath)
            .into(view.frag_poster_view)

        Glide.with(view.context.applicationContext)
            .load(IMAGE_URL + movie?.backdropPath)
            .into(view.frag_background_iv)

    }
}