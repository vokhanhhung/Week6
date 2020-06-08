package com.example.week6.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.week6.models.Movies
import com.example.week6.network.MovieService

class DataViewModel : ViewModel() {

    val getMoviesNowPlaying = liveData<Movies> {
        val movies = MovieService.getInstance().getApi().getNowPlayingMovies(1)
        emit(movies)
    }

    val getMoviesTopRating = liveData<Movies> {
        val movies = MovieService.getInstance().getApi().getTopRatingMovies(1)
        emit(movies)
    }


}