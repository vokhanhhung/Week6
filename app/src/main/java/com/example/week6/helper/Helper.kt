package com.example.week6.helper

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week6.adapter.MovieAdapterTypeGridLayout
import com.example.week6.adapter.MovieAdapterTypeLinearLayout
import com.example.week6.configure.Constant
import com.example.week6.listener.MovieListener
import com.example.week6.models.Movie
import com.example.week6.models.Movies

fun Context.toast(str: String) = Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
fun RecyclerView.updateUI(
    context: Context,
    movies: List<Movie>,
    listener: MovieListener,
    key_layout: String,
    key_fragment: String
) {
    when (key_layout) {
        Constant.LINEAR_LAYOUT -> {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = MovieAdapterTypeLinearLayout(context, movies, listener, key_fragment)
        }
        Constant.GRID_LAYOUT -> {
            this.layoutManager = GridLayoutManager(context, 3)
            this.adapter = MovieAdapterTypeGridLayout(context, movies,listener, key_fragment)
        }
    }
}

fun RecyclerView.updateUI(
    context: Context,
    movies: Movies,
    listener: MovieListener,
    key_layout: String,
    key_fragment: String
) {
    when (key_layout) {
        Constant.LINEAR_LAYOUT -> {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter =
                MovieAdapterTypeLinearLayout(context, movies.results, listener, key_fragment)
        }
        Constant.GRID_LAYOUT -> {
            this.layoutManager = GridLayoutManager(context, 3)
            this.adapter = MovieAdapterTypeGridLayout(context, movies.results,listener, key_fragment)
        }
    }
}
