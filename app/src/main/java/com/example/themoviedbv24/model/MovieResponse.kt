package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieResponse(

    @SerialName(value = "page")
    var page: Int = 0,

    @SerialName(value = "results")
    var results: List<Movie> = listOf(),

    @SerialName(value = "total_pages")
    var total_pages: Int = 0,

    @SerialName(value = "total_results")
    var total_results: Int = 0,
)
