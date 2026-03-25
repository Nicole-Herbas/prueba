package com.ucb.app.movie.data.repository

import com.ucb.app.movie.data.datasource.MovieRatingLocalDataSource
import com.ucb.app.movie.domain.repository.MovieRatingRepository

class MovieRatingRepositoryImpl(
    private val localDataSource: MovieRatingLocalDataSource
) : MovieRatingRepository {
    override fun getMovieRating(movieId: Int): Int = localDataSource.getRating(movieId)
    override fun setMovieRating(movieId: Int, rating: Int) = localDataSource.setRating(movieId, rating)
    override fun getAllRatings(): Map<Int, Int> = localDataSource.getAllRatings()
}
