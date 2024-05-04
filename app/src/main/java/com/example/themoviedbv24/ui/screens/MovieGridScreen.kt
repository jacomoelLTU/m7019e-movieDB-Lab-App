package com.example.themoviedbv24.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.PageSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.utils.Constants
import com.example.themoviedbv24.viewmodels.MovieListUiState


@Composable
fun MovieGridScreen(
    movieListUiState : MovieListUiState,
    onMovieListItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
)
{
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {

        when (movieListUiState) {
            is MovieListUiState.Success -> {
                items(movieListUiState.movies) { movie ->
                    MovieGridItemCard(
                        movie = movie,
                        onMovieListItemClicked,
                        modifier = Modifier
                            .padding(8.dp)
                            .aspectRatio(1.5f)
                    )
                }
            }

            is MovieListUiState.Loading -> {
                item {
                    Text(
                        text = "Loading...",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }

            is MovieListUiState.Error -> {
                item {
                    Text (
                        text = "Error: something went wrong",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieGridItemCard(
    movie: Movie,
    onMovieListItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        onClick =  {
            onMovieListItemClicked(movie)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(0.5f)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(shape = MaterialTheme.shapes.medium)
                ) {
                    AsyncImage(
                        model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                        contentDescription = movie.title,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier.weight(1.5f)
            ) {
                Text(
                    text = movie.title,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall.copy(fontSize = 16.sp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = movie.releaseDate,
                    maxLines = 1,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp)
                )
                Spacer(modifier = Modifier.size(4.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall.copy(fontSize = 10.sp),
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun MovieItemPreview() {
//    TheMovieDBV24Theme {
//        MovieListItemCard(
//            movie = Movie(
//                2,
//                "Road House",
//                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
//                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
//                "2024-03-08",
//                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
//                        "roadhouse, only to discover that this paradise is not all it seems."
//            ), {}
//        )
//    }
//}