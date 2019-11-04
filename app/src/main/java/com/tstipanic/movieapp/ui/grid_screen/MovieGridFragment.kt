package com.tstipanic.movieapp.ui.grid_screen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.SPAN_COUNT
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.ui.details_screen.MoviesPagerFragment
import com.tstipanic.movieapp.ui.grid_screen.adapters.MoviesGridAdapter
import com.tstipanic.movieapp.ui.search_screen.SearchActivity
import kotlinx.android.synthetic.main.fragment_grid.*
import kotlinx.android.synthetic.main.item_movie.*
import org.koin.android.ext.android.inject


class MovieGridFragment : Fragment(), MovieGridContract.View {

    private val presenter by inject<MovieGridContract.Presenter>()

    private val gridAdapter by lazy { MoviesGridAdapter({ onMovieClicked(it) }, { presenter.onFavoriteClicked(it) }) }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        presenter.setView(this)
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moviesGrid.apply {
            adapter = gridAdapter
            layoutManager = GridLayoutManager(context, SPAN_COUNT)
        }
        setUpNavigationListener()
        floatingAccount.setOnClickListener {
            LogoutFragment().show(fragmentManager, "Logout")
        }
        floatingSearch.setOnClickListener {
            startActivity(Intent(requireContext(), SearchActivity::class.java))
        }
    }



    override fun onResume() {
        super.onResume()
        presenter.onResume()
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

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
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
        fragmentManager!!
            .beginTransaction()
            .replace(R.id.mainFragmentHolder, MoviesPagerFragment.getInstance(presenter.getMovieList(), position))
            .addToBackStack(tag)
            .commit()
    }
}