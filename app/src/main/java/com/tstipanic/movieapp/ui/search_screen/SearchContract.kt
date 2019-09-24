package com.tstipanic.movieapp.ui.search_screen

import com.tstipanic.movieapp.model.data.Movie

interface SearchContract {

    interface View {

        fun setRecyclerList(movieList: List<Movie>)

        fun showProgress()

        fun hideProgress()

        fun showToast(message: String)

        fun setUnfavoriteIcon()

        fun setFavoriteIcon()

        fun refreshList()

        fun initiateIntent(movie: Movie)

        fun showNoResultImage()

        fun hideNoResultImage()

    }

    interface Presenter {

        fun setView(view: View)

        fun getMovies(keyword: String)

        fun onFavoriteClicked(movie: Movie)

        fun onIntent(movie: Movie)

    }
}