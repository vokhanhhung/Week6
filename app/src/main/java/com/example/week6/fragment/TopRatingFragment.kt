package com.example.week6.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.example.week6.R
import com.example.week6.R.layout.fragment_top_rating
import com.example.week6.configure.Constant.Companion.GRID_LAYOUT
import com.example.week6.configure.Constant.Companion.LINEAR_LAYOUT
import com.example.week6.configure.Constant.Companion.TOP_RATING_KEY
import com.example.week6.helper.updateUI
import com.example.week6.listener.MovieListener
import com.example.week6.models.Movie
import com.example.week6.room.MovieFavDao
import com.example.week6.room.MovieRoomDatabase
import com.example.week6.viewmodel.DataViewModel
import kotlinx.android.synthetic.main.fragment_top_rating.*

class TopRatingFragment : Fragment(), MovieListener {

    private lateinit var favMovies: List<Movie>
    private lateinit var db: MovieRoomDatabase
    private lateinit var dao: MovieFavDao
    private lateinit var dataViewModel: DataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_top_rating, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        dataViewModel = ViewModelProviders.of(requireActivity()).get(DataViewModel::class.java)
        dataViewModel.getMoviesTopRating.observe(viewLifecycleOwner, Observer {
            recyclerView_topRating.updateUI(
                requireContext(),
                it,
                this,
                LINEAR_LAYOUT,
                TOP_RATING_KEY
            )
        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.linear_layout -> {
                dataViewModel.getMoviesTopRating.observe(viewLifecycleOwner, Observer {
                    recyclerView_topRating.updateUI(
                        requireContext(),
                        it,
                        this,
                        LINEAR_LAYOUT,
                        TOP_RATING_KEY
                    )

                })
            }
            else -> {
                dataViewModel.getMoviesTopRating.observe(viewLifecycleOwner, Observer {
                    recyclerView_topRating.updateUI(
                        requireContext(),
                        it,
                        this,
                        GRID_LAYOUT,
                        TOP_RATING_KEY
                    )
                })
            }
        }
        return true
    }

    override fun onClickItemListener(position: Int, movie: Movie) {
        val bundle = bundleOf("movie" to movie)
        Navigation.findNavController(requireView())
            .navigate(R.id.action_topRatingFragment_to_overviewFragment, bundle)
    }

    override fun onClickFavoriteListener(position: Int, movie: Movie) {

    }
}