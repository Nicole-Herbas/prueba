package com.ucb.app.movie.presentation.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.ucb.app.movie.domain.model.MovieModel

@Composable
fun CardMovie(
    model: MovieModel,
    rating: Int = 0,
    onClick: () -> Unit = {},
    onRating: (Int) -> Unit = {}
) {
    val colors = MaterialTheme.colorScheme

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column {
            AsyncImage(
                model = model.pathUrl,
                contentDescription = model.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2f / 3f)
            )

            Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp)) {
                Text(
                    text = model.title,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                if (model.releaseDate.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = model.releaseDate,
                        style = MaterialTheme.typography.labelSmall,
                        color = colors.onSurfaceVariant,
                        maxLines = 1
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                StarRatingBar(
                    rating = rating,
                    starSize = 18.dp,
                    onRating = onRating
                )
            }
        }
    }
}

@Composable
fun StarRatingBar(
    rating: Int,
    starSize: Dp = 24.dp,
    maxStars: Int = 5,
    onRating: (Int) -> Unit = {}
) {
    val filledStar = "★"
    val emptyStar = "☆"
    val starFontSize = (starSize.value).sp

    Row {
        for (i in 1..maxStars) {
            Text(
                text = if (i <= rating) filledStar else emptyStar,
                fontSize = starFontSize,
                color = if (i <= rating)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier
                    .clickable { onRating(i) }
                    .padding(horizontal = 2.dp)
            )
        }
    }
}