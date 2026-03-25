package com.ucb.app.movie.domain.model

data class MovieModel(
    val id: Int = 0,
    val title: String,
    val pathUrl: String,
    val releaseDate: String,
    val overview: String = "",
    val voteAverage: Double = 0.0
)
