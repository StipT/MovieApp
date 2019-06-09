package com.tstipanic.movieapp.ui.grid_screen

interface MovieGridContract {

    interface View {

        fun setGridList()

        fun onMovieCallbackFailure()

        fun bottomNavState()

        fun setFavoriteIcon()

        fun setUnfavoriteIcon()

    }

    interface Presenter {

        fun setView(view: View)

        fun onPopularMenuSelected()

        fun onTopRatedMenuSelected()

        fun onFavoriteMenuSelected()

        fun onFavoriteClicked()


    }
}