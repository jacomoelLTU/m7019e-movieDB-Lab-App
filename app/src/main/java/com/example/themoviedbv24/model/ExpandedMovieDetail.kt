package com.example.themoviedbv24.model

data class ExpandedMovieDetail(
    //var id: Long = 0L,
    var genres: MutableList<String>,
    var status: String,
    var tagline: String,
    var homePageUrl: String?,
    var imdbId: String
)
