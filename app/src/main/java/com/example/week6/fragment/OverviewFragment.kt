package com.example.week6.fragment

import GenreAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.week6.R
import com.example.week6.models.GenreId
import com.example.week6.models.Movie
import com.example.week6.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_overview.*


class OverviewFragment : Fragment() {

    lateinit var movie: Movie
    private lateinit var dataViewModel: DataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable("movie")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataViewModel = ViewModelProviders.of(requireActivity()).get(DataViewModel::class.java)
        movie.let {
            val url = "https://image.tmdb.org/t/p/w500/" + (movie.posterPath)
            Glide.with(this).load(url).into(avatarMovie)
            vote_average.text = movie.voteAverage.toString()
            popularity.text = movie.popularity.toString() + " views"
            vote_count.text = movie.voteCount.toString() + " votes"
            adult.text = when (movie.adult) {
                true -> "Adult: yes"
                else -> "Adult: no"
            }
            recyclerview_geners.layoutManager = GridLayoutManager(context, 3)
            val genres: List<GenreId> = movie.genreIds.map { id ->
                when (id) {
                    28 -> GenreId(id, "Action")
                    12 -> GenreId(id, "Adventure")
                    16 -> GenreId(id, "Animation")
                    35 -> GenreId(id, "Comedy")
                    80 -> GenreId(id, "Crime")
                    99 -> GenreId(id, "Documentary")
                    18 -> GenreId(id, "Drama")
                    10751 -> GenreId(id, "Family")
                    14 -> GenreId(id, "Fantasy")
                    36 -> GenreId(id, "History")
                    27 -> GenreId(id, "Horror")
                    10402 -> GenreId(id, "Music")
                    9648 -> GenreId(id, "Mystery")
                    10749 -> GenreId(id, "Romance")
                    878 -> GenreId(id, "Science Fiction")
                    10770 -> GenreId(id, "TV Movie")
                    53 -> GenreId(id, "Thriller")
                    10752 -> GenreId(id, "War")
                    37 -> GenreId(id, "Western")
                    else -> GenreId(id, "I don't know")
                }
            }

            recyclerview_geners.adapter = GenreAdapter(genres, requireContext())

            ratingbar.rating = movie.voteAverage.toFloat() / 2
            language.text = "Language: " + movie.originalLanguage
            release_date.text = movie.releaseDate
            nameMovie.text = movie.nameMovie
            overview.text = movie.overview
        }
    }


}