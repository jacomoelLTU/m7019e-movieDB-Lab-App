package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExpandedMovieDetailsResponse(

    @SerialName(value = "genres")
    var genres: MutableList<String> = mutableListOf(),

    @SerialName(value = "status")
    var status: String = "",

    @SerialName(value = "tagline")
    var tagline: String? = null,

    @SerialName(value = "homepage")
    var homePageUrl: String? = null,

    @SerialName(value = "imdb_id")
    var imdbId: String
)
