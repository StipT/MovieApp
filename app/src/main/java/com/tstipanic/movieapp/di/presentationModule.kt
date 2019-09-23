package com.tstipanic.movieapp.di

import com.tstipanic.movieapp.presentation.MovieDetailsPresenter
import com.tstipanic.movieapp.presentation.MovieGridPresenter
import com.tstipanic.movieapp.presentation.SearchPresenter
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import com.tstipanic.movieapp.ui.grid_screen.MovieGridContract
import com.tstipanic.movieapp.ui.search_screen.SearchContract
import org.koin.dsl.module

val presentationModule = module {

    factory<MovieGridContract.Presenter> { MovieGridPresenter(get(), get()) }
    factory<MovieDetailsContract.Presenter> { MovieDetailsPresenter(get(), get()) }
    factory<SearchContract.Presenter> { SearchPresenter(get(), get()) }
}