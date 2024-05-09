package com.example.themoviedbv24.database

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.themoviedbv24.MovieDBApplication
import com.example.themoviedbv24.model.ExpandedMovieDetails
import com.example.themoviedbv24.model.Movie
import com.example.themoviedbv24.model.MovieResponse
import com.example.themoviedbv24.model.MovieReviewResponse
import com.example.themoviedbv24.model.MovieVideoResponse
import com.example.themoviedbv24.network.MovieDBApiService
import com.example.themoviedbv24.workers.CacheWorker

interface MoviesRepository {

    var latestFetch: MutableList<Movie>

    suspend fun getPopularMovies(): MovieResponse
    suspend fun getTopRatedMovies(): MovieResponse
    suspend fun getExpandedMovieDetails(movieId: Long): ExpandedMovieDetails
    suspend fun getMovieReviews(movieId: Long): MovieReviewResponse

    suspend fun getMovieVideos(movieId: Long): MovieVideoResponse


}

class NetworkMoviesRepository(context: Context, private val apiService: MovieDBApiService) : MoviesRepository {

    private val workManager = WorkManager.getInstance(context)
    override var latestFetch = mutableListOf<Movie>()
    private val applicationContext = context.applicationContext as MovieDBApplication


    override suspend fun getPopularMovies(): MovieResponse {
        val networkStatus = applicationContext.container.networkHandler.isNetworkAvailable()
        when (networkStatus) {
            true -> {
                this.latestFetch = apiService.getPopularMovies().results.toMutableList()
                val cacheWorker = OneTimeWorkRequestBuilder<CacheWorker>()
                    .build()
                workManager.enqueue(cacheWorker)
                return apiService.getPopularMovies()
            }
            false -> {
                return MovieResponse(
                    page = 0,
                    results = applicationContext.container.localMoviesRepository.getMovies(),
                    total_pages = 0,
                    total_results = applicationContext.container.localMoviesRepository.getMovies().size
                )
            }
        }
    }

    override suspend fun getTopRatedMovies(): MovieResponse {
        val networkStatus = applicationContext.container.networkHandler.isNetworkAvailable()
        when (networkStatus) {
            true -> {
                latestFetch = apiService.getTopRatedMovies().results.toMutableList()
                val cacheWorker = OneTimeWorkRequestBuilder<CacheWorker>()
                    .build()
                workManager.enqueue(cacheWorker)
                return apiService.getTopRatedMovies()
            }
            false -> {
                return MovieResponse(
                    page = 0,
                    results = applicationContext.container.localMoviesRepository.getMovies(),
                    total_pages = 0,
                    total_results = applicationContext.container.localMoviesRepository.getMovies().size
                )
            }
        }
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
    suspend fun insertMovie(movie: Movie)

    suspend fun getMovie(id: Long): Movie

    suspend fun getMovies(): List<Movie>

    suspend fun favoriteMovie(movie: Movie)

    suspend fun unfavoriteMovie(id: Long)

    suspend fun getFavoriteMovies(): List<Movie>

    suspend fun decacheMovies()
}

class LocalMoviesRepository(private val movieDao: MovieDao) : SavedMoviesRepository {
    override suspend fun insertMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun getMovie(id: Long): Movie {
        return movieDao.getMovie(id)
    }

    override suspend fun getMovies(): List<Movie> {
        return movieDao.getCachedMovies()
    }

    override suspend fun favoriteMovie(movie: Movie) {
        movieDao.insertMovie(movie)
    }

    override suspend fun unfavoriteMovie(id: Long) {
        movieDao.unfavoriteMovie(id)
    }

    override suspend fun getFavoriteMovies(): List<Movie> {
        return movieDao.getFavoriteMovies()
    }

    override suspend fun decacheMovies() {
        movieDao.decacheMovies()
    }
}
