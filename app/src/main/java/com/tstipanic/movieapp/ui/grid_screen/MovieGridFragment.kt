package com.tstipanic.movieapp.ui.grid_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.tstipanic.movieapp.MovieApp
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.EXTRA_POSITION
import com.tstipanic.movieapp.common.SPAN_COUNT
import com.tstipanic.movieapp.common.showFragment
import com.tstipanic.movieapp.local_database.MovieRepo
import com.tstipanic.movieapp.local_database.MovieRepoImpl
import com.tstipanic.movieapp.local_database.MoviesDatabase
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.interactor.MovieInteractor
import com.tstipanic.movieapp.model.response.MoviesResponse
import com.tstipanic.movieapp.networking.BackendFactory
import com.tstipanic.movieapp.presentation.MovieDetailsPresenter
import com.tstipanic.movieapp.presentation.MovieGridPresenter
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import com.tstipanic.movieapp.ui.details_screen.MoviesPagerFragment
import com.tstipanic.movieapp.ui.grid_screen.adapters.MoviesGridAdapter
import kotlinx.android.synthetic.main.fragment_grid.*
import kotlinx.android.synthetic.main.item_movie.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

    override fun setGridList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onMovieCallbackFailure() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun bottomNavState() {
        if (bottomNavBar.selectedItemId == R.id.menu_favorites) {
            presenter.onFavoriteClicked()
        }



    }

    override fun setFavoriteIcon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setUnfavoriteIcon() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun onMovieClicked(position: Int) {
        activity?.showFragment(
            R.id.mainFragmentHolder,
            MoviesPagerFragment.getInstance(movieList, position),
            true
        )
    }


}