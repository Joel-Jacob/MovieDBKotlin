package com.example.moviedbkotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviedbkotlin.R
import com.example.moviedbkotlin.model.Result
import kotlinx.android.synthetic.main.movie_item_layout.view.*

class MovieAdapter(val list:List<Result>, var delegate: MovieDelegate):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private lateinit var context : Context
    private val IMAGE_URL = "https://image.tmdb.org/t/p/w500/"

    interface MovieDelegate {
        fun viewItem(movie: Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        context = parent.context.applicationContext

        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        if (list.get(position).title.length >= 30)
            holder.title.textSize = 12f
        else if (list.get(position).title.length >= 20)
            holder.title.textSize = 16f

        holder.title.setText(list.get(position).title)
        holder.score.setText("Score: " +list.get(position).voteAverage.toString())
        holder.date.setText(list.get(position).releaseDate)

        Glide.with(context)
            .load(IMAGE_URL + list.get(position).posterPath)
            .into(holder.poster)

        val transition =
            AnimationUtils.loadAnimation(context, R.anim.transition_animation)
        holder.itemView.startAnimation(transition)

        holder.itemView.setOnClickListener {
            delegate.viewItem(list.get(position))
        }

    }

    class MovieViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var title : TextView = itemView.item_title_tv
        var score : TextView = itemView.item_score_tv
        var date : TextView = itemView.item_date_tv
        var poster: ImageView = itemView.item_image_view
    }
}