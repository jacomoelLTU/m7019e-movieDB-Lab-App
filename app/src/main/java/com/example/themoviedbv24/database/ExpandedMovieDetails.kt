package com.example.themoviedbv24.database

import com.example.themoviedbv24.model.ExpandedMovieDetail

class ExpandedMovieDetails {

    fun getExpandedMovieDetails () : List<ExpandedMovieDetail>  {
        return listOf<ExpandedMovieDetail> (
            ExpandedMovieDetail(
                //1,
                mutableListOf(
                    "Science Fiction",
                    "Action"
                ),
                "Released",
                "Hunt or be hunted.",
                null,
                "tt12774526"
            ),

            ExpandedMovieDetail(
                //2,
                mutableListOf(
                    "Action",
                    "Thriller"
                ),
                "Released",
                "Take it outside.",
                "https://www.amazon.com/gp/video/detail/B0CH5YQPZQ",
                "tt3359350"
            ),

            ExpandedMovieDetail(
               // 3,
                mutableListOf(
                    "Animation",
                    "Action",
                    "Adventure",
                    "Comedy",
                    "Family"
                ),
                "Released",
                "Odd ducks welcome.",
                "https://www.migration.movie",
                "tt6495056"
            ),

            ExpandedMovieDetail(
                //4,
                mutableListOf(
                    "Action",
                ),
                "Released",
                "Nowhere to run. Nowhere to hide.",
                null,
                "tt13936336"
            ),

            ExpandedMovieDetail(
                //5,
                mutableListOf(
                        "Science Fiction",
                        "Adventure"
                    ),
                "Released",
                "Long live the fighters.",
                "https://www.dunemovie.com",
                "tt15239678"
            ),

            ExpandedMovieDetail(
               // 6,
                mutableListOf(
                    "Science Fiction",
                    "Adventure"
                ),
                "Released",
                "It begins.",
                "https://www.dunemovie.com/",
                "tt1160419"
            )
        )
    }

}