package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(

    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName(value = "title")
    var title: String,

    @SerialName(value = "poster_path")
    var posterPath: String,

    @SerialName(value = "backdrop_path")
    var backdropPath: String,

    @SerialName(value = "release_date")
    var releaseDate: String,

    @SerialName(value = "overview")
    var overview: String,

    //Expanded Details
    @SerialName(value = "genres")
    var genres: List<Genre>?  = mutableListOf(),

    @SerialName(value = "status")
    var status: String = "",

    @SerialName(value = "tagline")
    var tagline: String? = null,

    @SerialName(value = "homepage")
    var homePageUrl: String? = null,

    @SerialName(value = "imdb_id")
    var imdbId: String
)
