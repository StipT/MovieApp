package com.tstipanic.movieapp.local_database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tstipanic.movieapp.model.data.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
}