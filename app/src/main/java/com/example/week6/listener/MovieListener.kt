package com.example.week6.listener

import com.example.week6.models.Movie
import java.text.FieldPosition

interface MovieListener {

    fun onClickItemListener(position: Int, movie : Movie)
    fun onClickFavoriteListener(position: Int, movie: Movie)

}