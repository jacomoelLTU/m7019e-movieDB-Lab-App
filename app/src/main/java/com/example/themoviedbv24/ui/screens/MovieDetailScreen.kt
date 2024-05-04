package com.example.themoviedbv24.ui.screens

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import coil.compose.AsyncImage
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.model.MovieReview
import com.example.themoviedbv24.model.MovieVideo
import com.example.themoviedbv24.ui.uiHelper.getReviewCardScreenWidth
import com.example.themoviedbv24.ui.uiHelper.getScreenWidth
import com.example.themoviedbv24.utils.Constants
import com.example.themoviedbv24.viewmodels.MovieDBViewModel
import com.example.themoviedbv24.viewmodels.SelectedMovieUiState


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun MovieDetailScreen (
    //selectedMovieUiState : SelectedMovieUiState,
    movieDBViewModel: MovieDBViewModel,
    onExpandDetailsClicked : (Movie) -> Unit,
    modifier : Modifier = Modifier,
) {
    val selectedMovieUiState = movieDBViewModel.selectedMovieUiState

    when (selectedMovieUiState) {

        is SelectedMovieUiState.Success -> {
            Column {
                Box {
                    AsyncImage( //TODO: change to modular composable like MovieReviewLazyRow
                        model = Constants.BACKDROP_IMAGE_BASE_URL + Constants.BACKDROP_IMAGE_WIDTH + selectedMovieUiState.movie.backdropPath,
                        contentDescription = selectedMovieUiState.movie.title,
                        modifier = modifier,
                        contentScale = ContentScale.Crop
                    )
                }
                Text( //TODO: change to modular composable like MovieReviewLazyRow
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
                Row () {
                    Text(
                        text = "Favorite",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Switch (checked = selectedMovieUiState.isFavorite, onCheckedChange = {
                        if (it) {
                            movieDBViewModel.saveMovie(selectedMovieUiState.movie)
                        } else {
                            movieDBViewModel.deleteMovie(selectedMovieUiState.movie)
                        }
                    })
                }

                Button(  //TODO: change to modular composable like MovieReviewLazyRow
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                    , onClick = {
                        onExpandDetailsClicked(selectedMovieUiState.movie)
                    }
                ) {
                    Text(text = "Show Expanded Details")
                }

                Column (modifier = Modifier
                    //.fillMaxWidth()
                    .padding(4.dp)
                    .verticalScroll(rememberScrollState())
                ) {

                    MovieReviewLazyRow(reviewList = selectedMovieUiState.movieReviewResponse.results)
                    Spacer(modifier = Modifier.size(8.dp))
                    //hard coded example vids
                    selectedMovieUiState.movieVideoResponse.results?.let { MovieVideosLazyRow(videoList = it) }
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
fun MovieReviewCard(
    movieReview: MovieReview,
) {
    Box {
        Card(modifier = Modifier
            .height(120.dp) //TODO: change this to expand to max height() through an expand button
            .width(getReviewCardScreenWidth())
            .padding(horizontal = 8.dp)
            .padding(vertical = 4.dp),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier
                .padding(16.dp),

            ) {
                Text(
                    text = "Author: " + movieReview.author,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = movieReview.content,
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun MovieReviewLazyRow(reviewList: List<MovieReview>){
    LazyRow(
        modifier = Modifier
    ) {
        reviewList?.let { reviewList ->
            items(reviewList) {review ->
                MovieReviewCard(review)
            }
        }
    }
}


@Composable
fun MovieVideosLazyRow(videoList: List<MovieVideo>) {
    LazyRow(
        modifier = Modifier
    ) {
        videoList?.let { videoList ->
            items(videoList) {video ->
                ExoPlayerView(video)
            }
        }
    }
}

/**
 * Composable function that displays an ExoPlayer to play a video using Jetpack Compose.
 *
 * @OptIn annotation to UnstableApi is used to indicate that the API is still experimental and may
 * undergo changes in the future.
 *
 * @see EXAMPLE_VIDEO_URI Replace with the actual URI of the video to be played.
 */
@OptIn(UnstableApi::class)
@Composable
fun ExoPlayerView(video: MovieVideo) {

    //TODO: not be the example video but one should build with the youtube base,
    //TODO: ytMediaSource should be used for each video
//    val ytMediaSource = remember(Constants.YOUTUBE_BASE_URL + video.key) {
//        MediaItem.fromUri(Constants.YOUTUBE_BASE_URL + video.key)
//    }

    // Get the current context
    val context = LocalContext.current

    // Initialize ExoPlayer
    val exoPlayer = ExoPlayer.Builder(context).build()

    // Create a MediaSource
    val mediaSource = remember(Constants.EXAMPLE_VIDEO_URI) {
        MediaItem.fromUri(Constants.EXAMPLE_VIDEO_URI)
    }

    // Set MediaSource to ExoPlayer
    LaunchedEffect(mediaSource) {
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.prepare()
    }

    // Manage lifecycle events
    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }

    // Use AndroidView to embed an Android View (PlayerView) into Compose
    AndroidView(
        factory = { ctx ->
            PlayerView(ctx).apply {
                player = exoPlayer
            }
        },
        modifier = Modifier
            .width(getScreenWidth())
            .height(180.dp) // Set your desired height
    )
}