package com.tstipanic.movieapp.di

import androidx.room.Room
import com.tstipanic.movieapp.local_database.MovieRepo
import com.tstipanic.movieapp.local_database.MovieRepoImpl
import com.tstipanic.movieapp.local_database.MoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), MoviesDatabase::class.java, "MoviesDatabase")
            .allowMainThreadQueries().build()
    }

    factory<MovieRepo> { MovieRepoImpl(get()) }
}