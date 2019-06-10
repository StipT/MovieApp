package com.tstipanic.movieapp.ui.grid_screen

import com.tstipanic.movieapp.model.data.Movie

interface MovieGridContract {

    interface View {

        fun setGridList(movieList: List<Movie>)

        fun onMovieCallbackFailure(t: Throwable)

        fun bottomNavState()

        fun setFavoriteIcon()

        fun setUnfavoriteIcon()

        fun refreshGrid()

        fun scrollOnTop()

        fun showProgress()

        fun hideProgress()

    }

    interface Presenter {

        fun setView(view: View)

        fun onResume()

        fun onPopularMenuSelected()

        fun onTopRatedMenuSelected()

        fun onFavoriteMenuSelected()

        fun onFavoriteClicked(movie: Movie)

        fun getMovieList(): ArrayList<Movie>
    }
}