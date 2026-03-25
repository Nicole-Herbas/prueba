package com.ucb.app.movie.domain.usecase

import com.ucb.app.movie.domain.repository.MovieRatingRepository

class GetMovieRatingUseCase(
    private val repository: MovieRatingRepository
) {
    fun invoke(movieId: Int): Int = repository.getMovieRating(movieId)
    fun getAllRatings(): Map<Int, Int> = repository.getAllRatings()
}
