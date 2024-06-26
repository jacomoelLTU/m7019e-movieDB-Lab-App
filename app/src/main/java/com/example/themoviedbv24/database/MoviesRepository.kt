package com.example.themoviedbv24.database

import com.example.themoviedbv24.model.ExpandedMovieDetails
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.model.MovieResponse
import com.example.themoviedbv24.model.MovieReviewResponse
import com.example.themoviedbv24.model.MovieVideoResponse
import com.example.themoviedbv24.network.MovieDBApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
    suspend fun getExpandedMovieDetails(movieId: Long): ExpandedMovieDetails
    suspend fun getMovieReviews(movieId: Long): MovieReviewResponse

    suspend fun getMovieVideos(movieId: Long): MovieVideoResponse
}

class NetworkMoviesRepository(private val apiService: MovieDBApiService) : MoviesRepository {

    override suspend fun getPopularMovies() : MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies() : MovieResponse {
        return apiService.getTopRatedMovies()
    }

    override suspend fun getExpandedMovieDetails(movieId: Long) : ExpandedMovieDetails {
        return apiService.getExpandedMovieDetails(movieId)
    }

    override suspend fun getMovieReviews(movieId: Long): MovieReviewResponse {
        return apiService.getMovieReviews(movieId)
    }

    override suspend fun getMovieVideos(movieId: Long): MovieVideoResponse {
        return apiService.getMovieVideos(movieId)
    }

}

interface SavedMoviesRepository {
    suspend fun getSavedMovies(): List<Movie>
    suspend fun insertMovie(movie: Movie)
    suspend fun getMovie(id: Long): Movie
    suspend fun deleteMovie(movie: Movie)
}

class FavoriteMoviesRepository(private val movieDao: MovieDao) : SavedMoviesRepository {
    override suspend fun getSavedMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }
    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertFavoriteMovie(movie)
    }
    override suspend fun getMovie(id: Long): Movie {
        return movieDao.getMovie(id)
    }
    override suspend fun deleteMovie(movie: Movie) {
        movieDao.deleteFavoriteMovie(movie.id)
    }
}
