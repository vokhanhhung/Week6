package com.example.week6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.week6.R
import com.example.week6.configure.Constant.Companion.FAVORITE_KEY
import com.example.week6.configure.Constant.Companion.GRID_LAYOUT
import com.example.week6.configure.Constant.Companion.LINEAR_LAYOUT
import com.example.week6.helper.updateUI
import com.example.week6.listener.MovieListener
import com.example.week6.models.Movie
import com.example.week6.room.MovieFavDao
import com.example.week6.room.MovieRoomDatabase
import com.example.week6.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteFragment : Fragment(), MovieListener {


    private lateinit var dataViewModel: DataViewModel
    private lateinit var dao: MovieFavDao
    private lateinit var movies: List<Movie>
    private lateinit var db: MovieRoomDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        GlobalScope.launch(Dispatchers.Default) {
            db = MovieRoomDatabase.invoke(requireContext())
            dao = db.movieDao()
            movies = dao.getAllFavoriteMovie()

            withContext(Dispatchers.Main) {
                recyclerView_favoriteMovies.updateUI(
                    requireContext(),
                    movies,
                    this@FavoriteFragment,
                    LINEAR_LAYOUT,
                    FAVORITE_KEY
                )
            }
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.linear_layout -> recyclerView_favoriteMovies.updateUI(
                requireContext(),
                movies,
                this,
                LINEAR_LAYOUT,
                FAVORITE_KEY
            )
            else -> recyclerView_favoriteMovies.updateUI(
                requireContext(),
                movies,
                this,
                GRID_LAYOUT,
                FAVORITE_KEY
            )
        }
        return true
    }

    override fun onClickItemListener(position: Int, movie: Movie) {
        val bundle = bundleOf("movie" to movie)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_favoriteFragment_to_overviewFragment, bundle)
    }

    override fun onClickFavoriteListener(position: Int, movie: Movie) {
    }
}