package com.ucb.app.movie.domain.usecase

import com.ucb.app.movie.domain.repository.MovieRatingRepository

class SetMovieRatingUseCase(
    private val repository: MovieRatingRepository
) {
    fun invoke(movieId: Int, rating: Int) = repository.setMovieRating(movieId, rating)
}
