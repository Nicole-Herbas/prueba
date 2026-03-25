package com.ucb.app.movie.domain.repository

interface MovieRatingRepository {
    fun getMovieRating(movieId: Int): Int
    fun setMovieRating(movieId: Int, rating: Int)
    fun getAllRatings(): Map<Int, Int>
}
