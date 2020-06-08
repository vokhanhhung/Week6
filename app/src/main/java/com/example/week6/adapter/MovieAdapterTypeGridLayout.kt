package com.example.week6.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week6.R
import com.example.week6.listener.MovieListener
import com.example.week6.models.Movie

class MovieAdapterTypeGridLayout(
    private val context: Context,
    private val movies: List<Movie>,
    private val listener: MovieListener,
    private val key: String
) :
    RecyclerView.Adapter<MovieAdapterTypeGridLayout.MovieViewHolder>() {


    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarMovie: ImageView = itemView.findViewById(R.id.avatarMovieGrid)

        init {
            itemView.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.movie_item_grid_layout, null, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        val url = "https://image.tmdb.org/t/p/w500/" + movie.posterPath

        //Load avatar for movie
        Glide
            .with(context)
            .load(url)
            .into(holder.avatarMovie)

        //Set event click item movie
        holder.avatarMovie.setOnClickListener {
            listener.onClickItemListener(position, movie)
        }
    }
}

