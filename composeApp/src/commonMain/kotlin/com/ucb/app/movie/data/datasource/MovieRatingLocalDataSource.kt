package com.ucb.app.movie.data.datasource

interface MovieRatingLocalDataSource {
    fun getRating(movieId: Int): Int
    fun setRating(movieId: Int, rating: Int)
    fun getAllRatings(): Map<Int, Int>
}
