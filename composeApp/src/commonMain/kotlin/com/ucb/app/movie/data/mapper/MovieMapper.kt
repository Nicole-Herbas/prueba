package com.ucb.app.movie.data.mapper

import com.ucb.app.movie.data.dto.MovieDto
import com.ucb.app.movie.domain.model.MovieModel

fun MovieDto.toModel() = MovieModel(
    id = id,
    pathUrl = "https://image.tmdb.org/t/p/w342$posterPath",
    title = title,
    releaseDate = releaseDate,
    overview = overview,
    voteAverage = voteAverage
)

