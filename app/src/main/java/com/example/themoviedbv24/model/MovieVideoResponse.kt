package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieVideoResponse(

    @SerialName(value = "id")
    var id: Long = 0L,

    @SerialName("results")
    var results: List<MovieVideo>? = listOf()
)
