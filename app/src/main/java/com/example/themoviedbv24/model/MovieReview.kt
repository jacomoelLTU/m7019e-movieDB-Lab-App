package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieReview(
    @SerialName(value = "author")
    var author: String,

    @SerialName(value = "content")
    var content: String
)
