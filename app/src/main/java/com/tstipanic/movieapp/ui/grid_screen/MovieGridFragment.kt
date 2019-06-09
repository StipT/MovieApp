package com.tstipanic.movieapp.ui.grid_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.SPAN_COUNT
import com.tstipanic.movieapp.common.showFragment
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.presentation.MovieGridPresenter
import com.tstipanic.movieapp.ui.details_screen.MoviesPagerFragment
import com.tstipanic.movieapp.ui.grid_screen.adapters.MoviesGridAdapter
import kotlinx.android.synthetic.main.fragment_grid.*
import kotlinx.android.synthetic.main.item_movie.*

class MovieGridFragment : Fragment(), MovieGridContract.View {

    private val presenter: MovieGridContract.Presenter by lazy { MovieGridPresenter()}

    private val gridAdapter by lazy {
        MoviesGridAdapter({ onMovieClicked(it) }, { presenter.onFavoriteClicked(it) })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_grid, container, false)

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
            setUpNavigationListener()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.onPopularMenuSelected()
    }

    private fun setUpNavigationListener() {
        bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_popular -> presenter.onPopularMenuSelected()
                R.id.menu_toprated -> presenter.onTopRatedMenuSelected()
                else -> presenter.onFavoriteMenuSelected()
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun setGridList(movieList: List<Movie>) {
        gridAdapter.setMovies(movieList)
    }

    override fun scrollOnTop() {
        moviesGrid.scrollToPosition(0)
    }

    override fun onMovieCallbackFailure(t: Throwable) {
        t.printStackTrace()
        //TODO toast
    }

    override fun refreshGrid() {
        gridAdapter.notifyDataSetChanged()
    }

    override fun bottomNavState() {
        if (bottomNavBar.selectedItemId == R.id.menu_favorites) {
            presenter.onFavoriteMenuSelected()
        }
    }

    override fun setFavoriteIcon() {
        movieFavorite.setImageResource(R.drawable.ic_favorite_full)
    }

    override fun setUnfavoriteIcon() {
        movieFavorite.setImageResource(R.drawable.ic_favorite_empty)
    }

    private fun onMovieClicked(position: Int) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(presenter.getMovieList(), position),
            true
        )
    }


}