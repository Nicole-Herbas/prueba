package com.ucb.app.movie.data.datasource

class MovieRatingLocalDataSourceImpl : MovieRatingLocalDataSource {
    private val ratings = mutableMapOf<Int, Int>()

    override fun getRating(movieId: Int): Int = ratings[movieId] ?: 0

    override fun setRating(movieId: Int, rating: Int) {
        ratings[movieId] = rating
    }

    override fun getAllRatings(): Map<Int, Int> = ratings.toMap()
}
