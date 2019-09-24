package com.tstipanic.movieapp.ui.search_screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.tstipanic.movieapp.R
import com.tstipanic.movieapp.common.EXTRA_BOOLEAN
import com.tstipanic.movieapp.common.EXTRA_OBJECT
import com.tstipanic.movieapp.common.hideKeyboard
import com.tstipanic.movieapp.model.data.Movie
import com.tstipanic.movieapp.ui.search_screen.search_recycler.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.item_search.*
import org.koin.android.ext.android.inject


class SearchActivity : AppCompatActivity(), SearchContract.View {

    private val presenter by inject<SearchContract.Presenter>()
    private val searchAdapter by lazy { SearchAdapter({ onMovieClicked(it) }, { presenter.onFavoriteClicked(it) }) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        presenter.setView(this)
        hideNoResultImage()
        setRecycler()

        backButton.setOnClickListener { onBackPressed() }
        val editorListener: TextView.OnEditorActionListener = TextView.OnEditorActionListener { _, _, _ ->
            onSearchClick()
            false
        }
        searchEditText.setOnEditorActionListener(editorListener)
        searchButton.setOnClickListener { onSearchClick() }
    }

    override fun onResume() {
        super.onResume()
        presenter.getMovies(searchEditText.text.toString())
    }

    private fun setRecycler() {
        searchRecycler.apply {
            adapter = searchAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun onSearchClick() {
        if (!searchEditText.text.isNullOrBlank()) {
            showProgress()
            presenter.getMovies((searchEditText.text.toString()))
            searchButton.hideKeyboard()
        }
    }


    override fun setRecyclerList(movieList: List<Movie>) {
        searchAdapter.setMovies(movieList)
        searchAdapter.notifyDataSetChanged()
    }

    override fun showProgress() {
        searchProgress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        searchProgress.visibility = View.GONE
    }

    override fun showToast(message: String) {
        Toast.makeText(baseContext, message, Toast.LENGTH_SHORT).show()
    }

    private fun onMovieClicked(movie: Movie) {
        presenter.onIntent(movie)
    }

    override fun refreshList() {
        searchAdapter.notifyDataSetChanged()
    }

    override fun setUnfavoriteIcon() {
        searchFavorite.setImageResource(R.drawable.ic_favorite_empty)
    }

    override fun setFavoriteIcon() {
        searchFavorite.setImageResource(R.drawable.ic_favorite_full)
    }

    override fun initiateIntent(movie: Movie) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra(EXTRA_OBJECT, movie)
        intent.putExtra(EXTRA_BOOLEAN, movie.isFavorite)
        startActivity(intent)
    }

    override fun showNoResultImage() {
        noResultsImage.visibility = View.VISIBLE
    }

    override fun hideNoResultImage() {
        noResultsImage.visibility = View.INVISIBLE
    }
}