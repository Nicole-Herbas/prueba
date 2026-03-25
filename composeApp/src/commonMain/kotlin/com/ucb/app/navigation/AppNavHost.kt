package com.ucb.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.ucb.app.github.presentation.screen.GithubScreen
import com.ucb.app.movie.presentation.screen.MovieDetailScreen
import com.ucb.app.movie.presentation.screen.MovieScreen

@Composable
fun AppNavHost() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = NavRoute.Movies) {
        composable<NavRoute.Profile> {
        }

        composable<NavRoute.ProfileEdit> {
        }

        composable<NavRoute.Github> {
            GithubScreen()
        }

        composable<NavRoute.Movies> {
            MovieScreen(navController = navController)
        }

        composable<NavRoute.MovieDetail> { backStackEntry ->
            val route: NavRoute.MovieDetail = backStackEntry.toRoute()
            MovieDetailScreen(
                route = route,
                navController = navController
            )
        }
    }
}
