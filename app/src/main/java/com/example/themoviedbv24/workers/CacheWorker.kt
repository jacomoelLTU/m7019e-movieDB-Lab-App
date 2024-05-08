package com.example.themoviedbv24.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.themoviedbv24.MovieDBApplication
import com.example.themoviedbv24.R

private const val TAG = "CacheWorker"
class CacheWorker(ctx: Context, params: WorkerParameters) : CoroutineWorker(ctx, params) {
    private val applicationContext = ctx.applicationContext as MovieDBApplication

    override suspend fun doWork(): Result {
        val appContainer = applicationContext.container

        return try {
            val movieList = appContainer.moviesRepository.latestFetch
            appContainer.localMoviesRepository.decacheMovies()
            movieList.forEach { fetchedMovie ->
                fetchedMovie.isCached = true
                appContainer.localMoviesRepository.insertMovie(fetchedMovie)
            }

            Result.success()
        } catch (throwable: Throwable) {
            Log.e(
                TAG,
                applicationContext.resources.getString(R.string.error_caching_movies),
                throwable
            )
            Result.failure()
        }
    }
}