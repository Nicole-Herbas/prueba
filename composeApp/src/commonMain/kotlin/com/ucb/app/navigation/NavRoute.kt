package com.ucb.app.navigation

import kotlinx.serialization.Serializable


@Serializable
sealed class NavRoute {

    @Serializable
    object Profile: NavRoute()

    @Serializable
    object ProfileEdit: NavRoute()

    @Serializable
    object Github: NavRoute()

    @Serializable
    object Movies: NavRoute()

    @Serializable
    data class MovieDetail(
        val id: Int,
        val title: String,
        val posterPath: String,
        val releaseDate: String,
        val overview: String,
        val voteAverage: Double
    ) : NavRoute()
}
