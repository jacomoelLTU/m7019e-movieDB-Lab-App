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
import com.example.themoviedbv24.model.Movie
import kotlinx.coroutines.launch
import java.io.IOException



sealed interface MovieListUiState {
    data class Success(val movies: List<Movie>) : MovieListUiState
    object Error : MovieListUiState
    object Loading : MovieListUiState
}

sealed interface SelectedMovieUiState {
    data class Success(val movie: Movie) : SelectedMovieUiState
    object Error : SelectedMovieUiState
    object Loading : SelectedMovieUiState


}


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class MovieDBViewModel(private val moviesRepository: MoviesRepository ) : ViewModel() {
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
                MovieListUiState.Success(moviesRepository.getTopRatedMovies().results)
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
                MovieListUiState.Success(moviesRepository.getPopularMovies().results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun getExpandedMovieDetails(movie : Movie) {
        viewModelScope.launch {
            movieListUiState = MovieListUiState.Loading
            movieListUiState = try {
                MovieListUiState.Success(moviesRepository.getExpandedMovieDetails(movie).results)
            } catch (e: IOException) {
                MovieListUiState.Error
            } catch (e: HttpException) {
                MovieListUiState.Error
            }
        }
    }

    fun setSelectedMovie(movie: Movie) {
        viewModelScope.launch {
            selectedMovieUiState = SelectedMovieUiState.Loading
            selectedMovieUiState = try {
                SelectedMovieUiState.Success(movie)
            } catch (e: IOException) {
                SelectedMovieUiState.Error
            } catch (e: HttpException) {
                SelectedMovieUiState.Error
            }
        }
    }



    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MovieDBApplication)
                val moviesRepository = application.container.moviesRepository
                MovieDBViewModel(moviesRepository = moviesRepository)
            }
        }
    }

//    fun getSavedMovies() {
//        viewModelScope.launch {
//            movieListUiState = MovieListUiState.Loading
//            movieListUiState = try {
//                MovieListUiState.Success(savedMovieRepository.getSavedMovies())
//            } catch (e: IOException) {
//                MovieListUiState.Error
//            } catch (e: HttpException) {
//                MovieListUiState.Error
//            }
//        }
//    }

//    fun saveMovie(movie: Movie) {
//        viewModelScope.launch {
//            savedMovieRepository.insertMovie(movie)
//            selectedMovieUiState = SelectedMovieUiState.Success(movie, true)
//        }
//    }

//    fun deleteMovie(movie: Movie) {
//        viewModelScope.launch {
//            savedMovieRepository.deleteMovie(movie)
//            selectedMovieUiState = SelectedMovieUiState.Success(movie, false)
//        }
//    }

}