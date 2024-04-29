package com.example.themoviedbv24.database

import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.model.MovieResponse
import com.example.themoviedbv24.network.MovieDBApiService

interface MoviesRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
}

class NetworkMoviesRepository(private val apiService: MovieDBApiService) : MoviesRepository {

    override suspend fun getPopularMovies() : MovieResponse {
        return apiService.getPopularMovies()
    }

    override suspend fun getTopRatedMovies() : MovieResponse {
        return apiService.getTopRatedMovies()
    }

}

//interface SavedMoviesRepository {
//    suspend fun getSavedMovies(): List<Movie>
//    suspend fun insertMovie(movie: Movie)
//    suspend fun getMovie(id: Long): Movie
//    suspend fun deleteMovie(movie: Movie)
//}

//class FavoriteMoviesRepository(private val movieDao: MovieDao) : SavedMoviesRepository {
//    override suspend fun getSavedMovies(): List<Movie> {
//        return movieDao.getFavoriteMovies()
//    }
//    override suspend fun insertMovie(movie: Movie) {
//        movieDao.insertFavoriteMovie(movie)
//    }
//    override suspend fun getMovie(id: Long): Movie {
//        return movieDao.getMovie(id)
//    }
//    override suspend fun deleteMovie(movie: Movie) {
//        movieDao.deleteFavoriteMovie(movie.id)
//    }
//}
