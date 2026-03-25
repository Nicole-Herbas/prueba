package com.ucb.app.movie.presentation.state

import com.ucb.app.movie.domain.model.MovieModel

sealed interface MovieEfffect {
    data class NavigateToDetail(val movie: MovieModel) : MovieEfffect
}
