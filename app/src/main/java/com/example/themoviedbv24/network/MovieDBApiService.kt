package com.example.themoviedbv24.network

import com.example.themoviedbv24.model.ExpandedMovieDetails
import com.example.themoviedbv24.model.MovieResponse
import com.example.themoviedbv24.model.MovieReviewResponse
import com.example.themoviedbv24.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApiService {
    @GET("popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): MovieResponse

    @GET("{movie_id}")
    suspend fun getExpandedMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : ExpandedMovieDetails

    @GET("{movie_id}/reviews")
    suspend fun getMovieReviews (
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : MovieReviewResponse

}