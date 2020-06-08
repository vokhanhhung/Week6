package com.example.week6.room

import androidx.room.*
import com.example.week6.models.Movie

@Dao
interface MovieFavDao {

    @Query("SELECT * FROM movie_table")
    suspend fun getAllFavoriteMovie(): List<Movie>

    @Update
    suspend fun updateMovie(movie: Movie)

    @Insert
    suspend fun addMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("DELETE FROM movie_table")
    suspend fun deleteAll()


}