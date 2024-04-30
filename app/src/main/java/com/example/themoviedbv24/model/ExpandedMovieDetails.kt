package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

//since this class only requests a single movie's details body it does not need a response class.
@Serializable
data class ExpandedMovieDetails(

    @SerialName(value = "genres")
    var genres: List<Genre>? = null, //mby needs to be of som genre object type

    @SerialName(value = "status")
    var status: String = "",

    @SerialName(value = "tagline")
    var tagline: String = "",

    @SerialName(value = "homepage")
    var homePageUrl: String? = null,

    @SerialName(value = "imdb_id")
    var imdbId: String = ""

)


