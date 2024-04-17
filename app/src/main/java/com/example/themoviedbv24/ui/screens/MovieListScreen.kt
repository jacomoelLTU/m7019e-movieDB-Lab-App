package com.example.themoviedbv24.ui.screens


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.themoviedbv24.model.ExpandedMovieDetail
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.ui.theme.TheMovieDBV24Theme
import com.example.themoviedbv24.utils.Constants


@Composable
fun MovieListScreen(
    movieList : List<Movie>,
    onMovieListItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
)
{
    LazyColumn(
        modifier = modifier
    ) {
        items(movieList) { movie ->
            MovieListItemCard(
                movie = movie,
                onMovieListItemClicked,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieListItemCard(
    movie: Movie,
    onMovieListItemClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
)
{
    Card(modifier = modifier,
         onClick = {
             onMovieListItemClicked(movie)
         }
        ) {
        Row {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    //contentScale = ContentScale.Crop
                )
            }
            Column {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.size(8.dp))
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieItemPreview() {
    TheMovieDBV24Theme {
        MovieListItemCard(
            movie = Movie(
                2,
                "Road House",
                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                "2024-03-08",
                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
                        "roadhouse, only to discover that this paradise is not all it seems.",
                ExpandedMovieDetail(
                    //2,
                    mutableListOf(
                        "Action",
                        "Thriller"
                    ),
                    "Released",
                    "Take it outside.",
                    "https://www.amazon.com/gp/video/detail/B0CH5YQPZQ",
                    "tt3359350"
                )
            ), {}
        )
    }
}