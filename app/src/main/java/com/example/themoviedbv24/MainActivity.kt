package com.example.themoviedbv24

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.database.Movies
import com.example.themoviedbv24.ui.theme.TheMovieDBV24Theme
import com.example.themoviedbv24.utils.Constants

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TheMovieDBV24Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TheMovieDBApp()
                }
            }
        }
    }
}

@Composable
fun TheMovieDBApp () {
    MovieList(movieList = Movies().getMovies())
}

@Composable
fun MovieList(movieList : List<Movie>, modifier: Modifier = Modifier) {
    LazyColumn (
        modifier = modifier
    ) {
        items(movieList) { movie ->
            movieListItemCard(
                movie = movie,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun movieListItemCard(movie: Movie, modifier: Modifier = Modifier) {
    Card(modifier = modifier) {
        Row () {
            Box {
                AsyncImage(
                    model = Constants.POSTER_IMAGE_BASE_URL + Constants.POSTER_IMAGE_WIDTH + movie.posterPath,
                    contentDescription = movie.title,
                    modifier = modifier
                        .width(92.dp)
                        .height(138.dp),
                    contentScale = ContentScale.Crop
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
                    text = movie.overwiew,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TheMovieDBV24Theme {
        movieListItemCard(
            movie = Movie(
                2,
                "Road House",
                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                "2024-03-08",
                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
                        "roadhouse, only to discover that this paradise is not all it seems."
            )
        )
    }
}