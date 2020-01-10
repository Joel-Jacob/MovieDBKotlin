package com.example.moviedbkotlin.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.moviedbkotlin.R
import com.example.moviedbkotlin.adapter.MovieAdapter
import com.example.moviedbkotlin.model.Result
import com.example.moviedbkotlin.viewmodel.MovieViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.movie_item_layout.*

class MainActivity : AppCompatActivity(), MovieAdapter.MovieDelegate{

    val compositeDisposable = CompositeDisposable()
    lateinit var viewModel: MovieViewModel
    lateinit var viewMovieFragment : ViewMovieFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        getMovieList("Batman")

        search_bt.setOnClickListener {
            search()
        }
    }

    private fun getMovieList(query:String){
        compositeDisposable.add(
            viewModel.getMovies(query).subscribe(){movieList->
                setRV(movieList.results)
            }
        )
    }

    private fun setRV(movies:List<Result>){
        recycler_view.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recycler_view.adapter = MovieAdapter(movies,this)

    }

    private fun search(){
        getMovieList(query_et.text.toString())
    }

    override fun viewItem(movie: Result) {
        Log.d("TAG_X", movie.title)

        var movieBundle = Bundle()
        movieBundle.putParcelable("selected_movie", movie)

        viewMovieFragment = ViewMovieFragment()
        viewMovieFragment.arguments = movieBundle

        supportFragmentManager
            .beginTransaction()
            .add(R.id.main_frame_layout, viewMovieFragment)
            .addToBackStack(viewMovieFragment.tag)
            .commit()

    }


    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
