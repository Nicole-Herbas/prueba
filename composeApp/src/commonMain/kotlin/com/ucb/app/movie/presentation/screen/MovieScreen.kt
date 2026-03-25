package com.ucb.app.movie.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ucb.app.movie.presentation.composable.CardMovie
import com.ucb.app.movie.presentation.state.MovieEfffect
import com.ucb.app.movie.presentation.state.MovieEvent
import com.ucb.app.movie.presentation.viewmodel.MovieViewModel
import com.ucb.app.navigation.NavRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MovieScreen(
    navController: NavController,
    viewModel: MovieViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Refresh ratings when arriving back from the detail screen
    LaunchedEffect(navController.currentBackStackEntry) {
        viewModel.refreshRatings()
    }

    // Consume one-shot navigation effects
    LaunchedEffect(Unit) {
        viewModel.effects.collect { effect ->
            when (effect) {
                is MovieEfffect.NavigateToDetail -> {
                    val movie = effect.movie
                    navController.navigate(
                        NavRoute.MovieDetail(
                            id = movie.id,
                            title = movie.title,
                            posterPath = movie.pathUrl,
                            releaseDate = movie.releaseDate,
                            overview = movie.overview,
                            voteAverage = movie.voteAverage
                        )
                    )
                }
            }
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(state.list.size) { index ->
                    val movie = state.list[index]
                    CardMovie(
                        model = movie,
                        rating = state.ratings[movie.id] ?: 0,
                        onClick = { viewModel.onEvent(MovieEvent.OnSelectMovie(movie)) },
                        onRating = { stars -> viewModel.onEvent(MovieEvent.OnRateMovie(movie.id, stars)) }
                    )
                }
            }
        }
    }
}