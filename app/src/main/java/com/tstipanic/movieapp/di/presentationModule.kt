package com.tstipanic.movieapp.di

import com.tstipanic.movieapp.presentation.MovieDetailsPresenter
import com.tstipanic.movieapp.presentation.MovieGridPresenter
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import com.tstipanic.movieapp.ui.grid_screen.MovieGridContract
import org.koin.dsl.module

val presentationModule = module {

    factory<MovieGridContract.Presenter> { MovieGridPresenter(get(), get()) }
    factory<MovieDetailsContract.Presenter> { MovieDetailsPresenter(get()) }
}