package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

// Class to extract JSON data from TMDB GET response.

// match up the obj with the top fields of TMDB response, below is an example from popular
// movie list endpoint.
// {
//   "page" : 1,
//   "results" : [<->],
//   "total_pages" : 43799,
//   "total_results" : 875980
//  }

@Serializable
data class MovieResponse (

    @SerialName(value = "page")
    var page: Int = 0,

    @SerialName(value = "total_pages")
    var totalPages: Int = 0,

    @SerialName(value = "total_results")
    var totalResults: Int = 0,

    @SerialName(value = "total_pages")
    var results: List<Movie> = listOf(),
)