package com.tstipanic.movieapp.ui.details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.PAGER_LIST_EXTRA
import com.tstipanic.movieapp.common.PAGER_SELECTED_POSITION_EXTRA
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.ui.details_screen.adapters.MoviePagerAdapter
import kotlinx.android.synthetic.main.fragment_pager.*

class MoviesPagerFragment : Fragment() {

    companion object {
        fun getInstance(movieList: ArrayList<Movie>, position: Int): MoviesPagerFragment {
            val pagerFragment = MoviesPagerFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList(PAGER_LIST_EXTRA, movieList)
            bundle.putInt(PAGER_SELECTED_POSITION_EXTRA, position)
            pagerFragment.arguments = bundle
            return pagerFragment
        }
    }

    private val pagerAdapter by lazy { MoviePagerAdapter(childFragmentManager) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val movieList = arrayListOf<Movie>()
         movieList.addAll(arguments?.getParcelableArrayList(PAGER_LIST_EXTRA)!!)
        moviePager.adapter = pagerAdapter

        pagerAdapter.setMovies(movieList)
        moviePager.currentItem = arguments?.getInt(PAGER_SELECTED_POSITION_EXTRA)!!
    }

}