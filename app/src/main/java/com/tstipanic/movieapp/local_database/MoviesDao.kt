package com.tstipanic.movieapp.local_database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.tstipanic.movieapp.model.data.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = REPLACE)
    fun addFavoriteMovie(movie: Movie)

    @Delete
    fun deleteFavoriteMovie(movie: Movie)

    @Query("SELECT * FROM Movie")
    fun getFavoriteMovies(): List<Movie>
}