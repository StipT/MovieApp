package com.tstipanic.movieapp.local_database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tstipanic.movieapp.model.data.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        private var instance: MoviesDatabase? = null

        fun getInstance(context: Context): MoviesDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, MoviesDatabase::class.java, "MoviesDatabase")
                    .allowMainThreadQueries().build()
            }
            return instance as MoviesDatabase
        }
    }
}