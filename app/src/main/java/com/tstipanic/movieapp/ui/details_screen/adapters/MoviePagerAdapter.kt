package com.tstipanic.movieapp.ui.details_screen.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsFragment

class MoviePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {

    private val moviesList = mutableListOf<Movie>()

    fun setMovies(moviesList: List<Movie>) {
        this.moviesList.clear()
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment = MovieDetailsFragment.getInstance(moviesList[position])

    override fun getCount(): Int = moviesList.size

}