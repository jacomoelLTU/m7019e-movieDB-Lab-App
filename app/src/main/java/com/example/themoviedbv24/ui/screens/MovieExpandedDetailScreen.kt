package com.example.themoviedbv24.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.themoviedbv24.model.Genre
import com.example.themoviedbv24.model.Movie

import com.example.themoviedbv24.utils.Constants
import com.example.themoviedbv24.viewmodels.SelectedMovieUiState

@Composable
fun MovieExpandedDetailScreen (
    selectedMovieUiState: SelectedMovieUiState,
    modifier : Modifier = Modifier,
) {
    when (selectedMovieUiState) {

        is SelectedMovieUiState.Success -> {
            Column {
                Box (
                    modifier = androidx.compose.ui.Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    //.background(MaterialTheme.colorScheme.onPrimary),
                    contentAlignment = Alignment.Center,

                    ) {
                    Text(
                        text = selectedMovieUiState.movie.title,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 52.sp,
                        maxLines = 1,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                Text(
                    text = "Tag line: " + selectedMovieUiState.movie.tagline,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = "release status: " + selectedMovieUiState.movie.status,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    maxLines = 1,
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column {
                    Text(
                        text = "Genres",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    LazyRow (
                        modifier = Modifier.padding(4.dp)
                    ) {
                        items(selectedMovieUiState.movie.genres) { genre ->
                            GenreCard(genre = genre)
                        }
                    }
                }
                Spacer(modifier = Modifier.size(8.dp))
                Row (
                    modifier = Modifier.padding(8.dp)
                ) {
                    BrowserButton(
                        selectedMovieUiState.movie.homePageUrl,
                        Modifier.weight(1f)
                    )
                    ImdbButton(
                        imdb_id = Constants.IMDB_BASE_URL + selectedMovieUiState.movie.imdbId,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        is SelectedMovieUiState.Loading -> {
            Text(
                text = "Loading..",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }

        is SelectedMovieUiState.Error -> {
            Text(
                text = "Error...",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(16.dp)
            )
        }

    }

}

@Composable
fun GenreCard(genre: Genre) {
    Surface (
        color = Color.LightGray,
        shadowElevation = 4.dp,
        modifier = androidx.compose.ui.Modifier.padding(8.dp)
    ) {
        Text(
            text = genre.name,
            style = MaterialTheme.typography.bodyMedium,
            modifier = androidx.compose.ui.Modifier.padding(8.dp)
        )

    }
}

@Composable
fun BrowserButton (
    homePageUrl: String?,
    modifier: androidx.compose.ui.Modifier
) {
    val context = LocalContext.current
    if (homePageUrl != null) {
        Button(
            onClick = {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(homePageUrl))
                val chooser = Intent.createChooser(intent, "Open link with")
                context.startActivity(chooser)
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
           Text(text = "HomePage")
        }
    }
}

@Composable
fun ImdbButton (
    imdb_id: String,
    modifier: androidx.compose.ui.Modifier
) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(imdb_id))
            context.startActivity(intent)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "IMDB")
    }

}