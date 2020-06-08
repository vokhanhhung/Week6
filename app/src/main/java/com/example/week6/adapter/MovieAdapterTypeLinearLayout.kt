package com.example.week6.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.week6.R
import com.example.week6.configure.Constant
import com.example.week6.helper.toast
import com.example.week6.listener.MovieListener
import com.example.week6.models.Movie
import com.example.week6.room.MovieRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieAdapterTypeLinearLayout(
    private val context: Context,
    private var movies: List<Movie>,
    private val listener: MovieListener,
    private val key: String
) :
    RecyclerView.Adapter<MovieAdapterTypeLinearLayout.MovieViewHolder>() {


    private lateinit var favMovies: List<Movie>

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var avatarMovie: ImageView = itemView.findViewById(R.id.avatarMovieLinear)
        var nameMovie: TextView = itemView.findViewById(R.id.nameMovieLinear)
        var ratingMovie: RatingBar = itemView.findViewById(R.id.ratingMovieLinear)
        var favMovie: ImageButton = itemView.findViewById(R.id.favMovieLinear)

        init {
            itemView.layoutParams = RecyclerView.LayoutParams(
                RecyclerView.LayoutParams.MATCH_PARENT,
                RecyclerView.LayoutParams.WRAP_CONTENT
            )

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.movie_item_linear_layout, null, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movies[position]

        val url = "https://image.tmdb.org/t/p/w500/" + movie.posterPath

        when (key) {
            Constant.FAVORITE_KEY -> holder.favMovie.setBackgroundResource(R.drawable.delete_icon)
            else -> holder.favMovie.setBackgroundResource(R.drawable.favorite_icon)
        }

        //Load avatar for movie
        Glide
            .with(context)
            .load(url)
            .into(holder.avatarMovie)

        //Set name for movie
        holder.nameMovie.text = movie.nameMovie

        //Set value for rating bar
        holder.ratingMovie.rating = movie.voteAverage.toFloat() / 2

        holder.avatarMovie.setOnClickListener {
            listener.onClickItemListener(position, movie)
        }



        holder.favMovie.setOnClickListener {
            listener.onClickFavoriteListener(position, movie)
            when (key) {
                Constant.FAVORITE_KEY -> {
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage("Bạn có muốn xóa phim này ra khỏi danh sách yêu thích không ")
                        .setPositiveButton("Có") { _, _ ->
                            GlobalScope.launch(Dispatchers.Default) {
                                val db = MovieRoomDatabase.invoke(context)
                                val dao = db.movieDao()
                                val moviesFav = dao.getAllFavoriteMovie()
                                dao.deleteMovie(moviesFav[position])
                            }
                        }
                        .setNegativeButton("Không") { dialog, _ ->
                            dialog.dismiss()
                        }.create()
                        .show()
                }

                else -> {
                    val builder = AlertDialog.Builder(context)
                    builder.setMessage("Bạn có muốn thêm bộ phim này vào danh sách yêu thích không?")
                        .setPositiveButton("Có") { dialog, which ->
                            //Add data into database favorite movie
                            GlobalScope.launch(Dispatchers.Default) {

                                val db = MovieRoomDatabase.invoke(context)
                                val dao = db.movieDao()
                                val moviesFav = dao.getAllFavoriteMovie()

                                withContext(Dispatchers.Main) {
                                    val ids = moviesFav.map { it.id }
                                    if (!ids.contains(movie.id)) {
                                        dao.addMovie(movie)
                                        context.toast("Đã thêm phim này vào danh sách yêu thích")
                                    } else context.toast("Phim đã được thêm vào danh sách yêu ")
                                }
                            }


                        }
                        .setNegativeButton("Không") { dialog, which ->
                            dialog.dismiss()
                        }
                        .create()
                        .show()
                }

            }
        }

    }
}

