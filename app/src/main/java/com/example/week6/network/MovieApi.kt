package com.example.week6.network

import com.example.week6.models.GenreId
import com.example.week6.models.Movie
import com.example.week6.models.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//https://api.themoviedb.org/3/movie/now_playing?api_key=1f3ed07e0672acd5f4e504e045e50535&language=en-US&page=1


interface MovieApi {

    //Get now playing movie list
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("page") page:Int) : Movies

    //Get top rating movie list
    @GET("movie/top_rated")
    suspend fun getTopRatingMovies(@Query("page") page:Int) : Movies


}