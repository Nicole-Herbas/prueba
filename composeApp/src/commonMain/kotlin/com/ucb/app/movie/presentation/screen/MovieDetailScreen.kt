package com.ucb.app.movie.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.ucb.app.movie.presentation.composable.StarRatingBar
import com.ucb.app.movie.presentation.viewmodel.MovieDetailViewModel
import com.ucb.app.navigation.NavRoute
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    route: NavRoute.MovieDetail,
    navController: NavController,
    viewModel: MovieDetailViewModel = koinViewModel()
) {
    LaunchedEffect(route.id) {
        viewModel.loadRating(route.id)
    }

    val rating by viewModel.rating.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(route.title, maxLines = 1, style = MaterialTheme.typography.titleMedium) },
                navigationIcon = {
                    Text(
                        text = "←",
                        fontSize = 24.sp,
                        modifier = Modifier
                            .clickable { navController.popBackStack() }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Large poster
            AsyncImage(
                model = route.posterPath,
                contentDescription = route.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
            )

            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Title
                Text(
                    text = route.title,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )

                // Meta row: release date + vote_average chip
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    if (route.releaseDate.isNotEmpty()) {
                        Text(
                            text = "📅 ${route.releaseDate}",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    if (route.voteAverage > 0.0) {
                        SuggestionChip(
                            onClick = {},
                            label = {
                                Text(
                                    text = "★ ${"%.1f".format(route.voteAverage)}",
                                    style = MaterialTheme.typography.labelMedium
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer
                            )
                        )
                    }
                }

                HorizontalDivider()

                // Overview
                if (route.overview.isNotEmpty()) {
                    Text(
                        text = "Sinopsis",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = route.overview,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                HorizontalDivider()

                // Rating section
                Text(
                    text = "Tu calificación",
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold
                )
                StarRatingBar(
                    rating = rating,
                    starSize = 32.dp,
                    onRating = { stars -> viewModel.setRating(route.id, stars) }
                )
                if (rating > 0) {
                    Text(
                        text = "$rating de 5 estrellas",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}
