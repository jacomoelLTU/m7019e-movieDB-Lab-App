package com.example.themoviedbv24.viewmodels

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.themoviedbv24.MovieDBApplication
import com.example.themoviedbv24.database.MoviesRepository
import com.example.themoviedbv24.database.SavedMoviesRepository

import com.example.themoviedbv24.model.ExpandedMovieDetails
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.model.MovieReviewResponse
import com.example.themoviedbv24.model.MovieVideoResponse
import kotlinx.coroutines.launch
import java.io.IOException



sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>) : MovieListUiState
    object Error : MovieListUiState
    object Loading : MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: Movie,
                       val expandedMovieDetails: ExpandedMovieDetails,
                       val movieReviewResponse: MovieReviewResponse,
                       val movieVideoResponse: MovieVideoResponse,
    ) : SelectedMovieUiState
    object Error : SelectedMovieUiState
    object Loading : SelectedMovieUiState


}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MovieDBViewModel(private val moviesRepository: MoviesRepository,
                       private val localMoviesRepository: SavedMoviesRepository
    ) : ViewModel() {

    var movieListUiState: MovieListUiState by mutableStateOf(MovieListUiState.Loading)
        private set
    var selectedMovieUiState: SelectedMovieUiState by mutableStateOf(SelectedMovieUiState.Loading)
        private set

    init {
        getPopularMovies()
    }

    fun getTopRatedMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                val fetchedMovies =  moviesRepository.getTopRatedMovies().results
                MovieListUiState.Success(fetchedMovies)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getPopularMovies() {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                val fetchedMovies =  moviesRepository.getPopularMovies().results
                MovieListUiState.Success(fetchedMovies)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getCachedMovies(){
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(localMoviesRepository.getMovies())
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun favoriteMovie(movie: Movie, details: ExpandedMovieDetails, reviews: MovieReviewResponse, videos: MovieVideoResponse) {
        movie.isFavorite = true
        viewModelScope.launch {
            localMoviesRepository.insertMovie(movie)
            selectedMovieUiState = SelectedMovieUiState.Success(movie, ExpandedMovieDetails(), MovieReviewResponse(), MovieVideoResponse())
        }
    }

    fun unfavoriteMovie(movie: Movie, details: ExpandedMovieDetails, reviews: MovieReviewResponse, videos: MovieVideoResponse) {
        movie.isFavorite = false
        viewModelScope.launch {
            localMoviesRepository.unfavoriteMovie(movie.id)
            selectedMovieUiState = SelectedMovieUiState.Success(movie, ExpandedMovieDetails(), MovieReviewResponse(), MovieVideoResponse())
        }
    }

    fun setSelectedMovie(movie: Movie) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            try {
                val details = moviesRepository.getExpandedMovieDetails(movie.id)
                val reviews = moviesRepository.getMovieReviews(movie.id)
                val videos = moviesRepository.getMovieVideos(movie.id)
                selectedMovieUiState = SelectedMovieUiState.Success(movie,
                                                                    details,
                                                                    reviews,
                                                                    videos
                                                                    )
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }

//    fun saveMovie(movie: Movie) {
//        viewModelScope.launch{
//            localMoviesRepository.insertMovie(movie)
//            selectedMovieUiState = SelectedMovieUiState.Success(movie,
//                                                                moviesRepository.getExpandedMovieDetails(movie.id),
//                                                                moviesRepository.getMovieReviews(movie.id),
//                                                                moviesRepository.getMovieVideos(movie.id),
//                                                                )
//        }
//    }

//    fun deleteMovie(movie: Movie) {
//        viewModelScope.launch {
//            localMoviesRepository.deleteMovie(movie)
//            selectedMovieUiState = SelectedMovieUiState.Success(movie,
//                                                                moviesRepository.getExpandedMovieDetails(movie.id),
//                                                                moviesRepository.getMovieReviews(movie.id),
//                                                                moviesRepository.getMovieVideos(movie.id),
//                                                                )
//        }
//    }





    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieDBApplication)
                val moviesRepository = application.container.moviesRepository
                val localMoviesRepository = application.container.localMoviesRepository
                MovieDBViewModel(moviesRepository = moviesRepository, localMoviesRepository = localMoviesRepository)
            }
        }
    }

}