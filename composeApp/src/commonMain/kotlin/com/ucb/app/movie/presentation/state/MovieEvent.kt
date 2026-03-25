package com.ucb.app.movie.presentation.state

import com.ucb.app.movie.domain.model.MovieModel

sealed interface MovieEvent {
    data class OnSelectMovie(val movie: MovieModel) : MovieEvent
    data class OnRateMovie(val movieId: Int, val rating: Int) : MovieEvent
}
