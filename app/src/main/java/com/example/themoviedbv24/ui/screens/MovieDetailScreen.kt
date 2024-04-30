package com.example.themoviedbv24.ui.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.utils.Constants
import com.example.themoviedbv24.viewmodels.MovieDBViewModel
import com.example.themoviedbv24.viewmodels.SelectedMovieUiState


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieDetailScreen (
    selectedMovieUiState : SelectedMovieUiState,
    onExpandDetailsClicked : (Movie) -> Unit,
    modifier : Modifier = Modifier,
) {

    when (selectedMovieUiState) {

        is SelectedMovieUiState.Success -> {
            Column {
                Box {
                    AsyncImage(
                        model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_WIDTH + selectedMovieUiState.movie.backdropPath,
                        contentDescription = selectedMovieUiState.movie.title,
                        modifier = modifier,
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = selectedMovieUiState.movie.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = selectedMovieUiState.movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = selectedMovieUiState.movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(8.dp))
                Button(
                    modifier = Modifier.fillMaxWidth(), onClick = {
                        onExpandDetailsClicked(selectedMovieUiState.movie)
                    }
                ) {
                    Text(text = "Show Expanded Details")
                }

                // Col woth LazyRow should mby be inside parent Col
            }

            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
                .verticalScroll(rememberScrollState())
            ) {
                LazyRow(
                    modifier = Modifier
                        .padding(4.dp)
                ) {

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


@Composable
fun MovieReviewCard(
    author: String,
    reviewContent: String
) {

}

}