package com.tstipanic.movieapp.ui.details_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.MOVIE_EXTRA
import com.tstipanic.movieapp.common.loadImage
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.model.data.Review

import com.tstipanic.movieapp.presentation.MovieDetailsPresenter
import com.tstipanic.movieapp.ui.details_screen.adapters.ReviewAdapter
import kotlinx.android.synthetic.main.fragment_details.*

class MovieDetailsFragment : Fragment(), MovieDetailsContract.View {

    private val reviewsAdapter by lazy { ReviewAdapter() }
    private val presenter: MovieDetailsContract.Presenter by lazy { MovieDetailsPresenter() }

    private lateinit var movie: Movie


    companion object {
        fun getInstance(movie: Movie): MovieDetailsFragment {
            val fragment = MovieDetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(MOVIE_EXTRA, movie)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun setReviewList(list: List<Review>) {
        reviewsAdapter.setReviewList(list)
    }

    override fun onReviewCallbackFailure(t: Throwable) {
        t.printStackTrace()
        //TODO toast
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movie = arguments?.getParcelable(MOVIE_EXTRA) as Movie
        initUi()
        presenter.getReviews(movie)
    }

    private fun initUi() {
        movieImage.loadImage(movie.poster)
        movieTitle.text = movie.title
        movieOverview.text = movie.overview
        movieReleaseDate.text = movie.releaseDate
        movieVoteAverage.text = movie.averageVote.toString()
        if (movie.isFavorite) {
            favoriteIcon.visibility = View.VISIBLE
        } else {
            favoriteIcon.visibility = View.GONE
        }

        movieReviewList.apply {
            adapter = reviewsAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


}