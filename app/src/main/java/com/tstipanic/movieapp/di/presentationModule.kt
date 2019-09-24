package com.tstipanic.movieapp.di

import com.tstipanic.movieapp.presentation.*
import com.tstipanic.movieapp.ui.auth_screen.LoginContract
import com.tstipanic.movieapp.ui.auth_screen.RegisterContract
import com.tstipanic.movieapp.ui.details_screen.MovieDetailsContract
import com.tstipanic.movieapp.ui.grid_screen.MovieGridContract
import com.tstipanic.movieapp.ui.search_screen.SearchContract
import org.koin.dsl.module

val presentationModule = module {

    factory<LoginContract.Presenter> { LoginPresenter() }
    factory<RegisterContract.Presenter> { RegisterPresenter() }
    factory<MovieGridContract.Presenter> { MovieGridPresenter(get(), get()) }
    factory<MovieDetailsContract.Presenter> { MovieDetailsPresenter(get(), get()) }
    factory<SearchContract.Presenter> { SearchPresenter(get(), get()) }
}