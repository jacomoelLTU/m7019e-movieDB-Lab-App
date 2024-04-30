package com.example.themoviedbv24.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Genre(

    @SerialName(value = "id")
    val id : Long,

    @SerialName(value = "name")
    val name : String
)
