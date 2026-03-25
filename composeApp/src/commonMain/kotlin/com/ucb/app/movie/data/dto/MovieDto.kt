package com.ucb.app.movie.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDto(
    val id: Int = 0,
    @SerialName("title")
    val title: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("release_date")
    val releaseDate: String = "",
    val overview: String = "",
    @SerialName("vote_average")
    val voteAverage: Double = 0.0
)

