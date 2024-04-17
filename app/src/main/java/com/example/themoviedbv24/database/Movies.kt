package com.example.themoviedbv24.database

import com.example.themoviedbv24.model.ExpandedMovieDetail
import com.example.themoviedbv24.model.Movie

class Movies {
    fun getMovies(): List<Movie> {
        return listOf<Movie> (

            Movie(1,
                "After the Pandemic",
                "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg",
                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                "2022-03-01",
                "Set in a post-apocalyptic world where a global airborne pandemic has wiped " +
                        "out 90% of the Earth's population and only the young and immune have endured " +
                        "as scavengers. For Ellie and Quinn, the daily challenges to stay alive are " +
                        "compounded when they become hunted by the merciless Stalkers.",
                ExpandedMovieDetail(
                   // 1,
                    mutableListOf(
                        "Science Fiction",
                        "Action"
                    ),
                    "Released",
                    "Hunt or be hunted.",
                    null,
                    "tt12774526"
                )),

            Movie(2,
                "Road House",
                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                "/oe7mWkvYhK4PLRNAVSvonzyUXNy.jpg",
                "2024-03-08",
                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
                        "roadhouse, only to discover that this paradise is not all it seems.",
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
                )),

            Movie(3,
                "Migration",
                "/ldfCF9RhR40mppkzmftxapaHeTo.jpg",
                "/2KGxQFV9Wp1MshPBf8BuqWUgVAz.jpg",
                "2023-12-06",
                "After a migrating duck family alights on their pond with thrilling tales " +
                        "of far-flung places, the Mallard family embarks on a family road trip, " +
                        "from New England, to New York City, to tropical Jamaica.",
                ExpandedMovieDetail(
                    //3,
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
                )),

            Movie(4,
                "Hunters",
                "/3UKlVa1CBeQkRksHV5OfFTO52qd.jpg",
                "/rKmp0vm6PNaFA0g1bzM70eyWJ6I.jpg",
                "2021-09-13",
                 "As John T. Wrecker continues his task of protecting a group of refugees " +
                         "from a virus, the threat of something new and even more dangerous " +
                         "grows ever closer in the form of monstrous mutants.",
                ExpandedMovieDetail(
                    //4,
                    mutableListOf(
                        "Action",
                    ),
                    "Released",
                    "Nowhere to run. Nowhere to hide.",
                    null,
                    "tt13936336"
                )),

            Movie(5,
                "Dune: Part Two",
                "/1pdfLvkbY9ohJlCjQH2CZjjYVvJ.jpg",
                "/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                "2024-02-27",
                "Follow the mythic journey of Paul Atreides as he unites with Chani and the " +
                        "Fremen while on a path of revenge against the conspirators who destroyed his family." +
                        " Facing a choice between the love of his life and the fate of the known universe, " +
                        "Paul endeavors to prevent a terrible future only he can foresee.",
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
                )),

            Movie(6,
                "Dune",
                "/d5NXSklXo0qyIYkgV94XAgMIckC.jpg",
                "/lzWHmYdfeFiMIY4JaMmtR7GEli3.jpg",
                "2021-09-15",
                "Paul Atreides, a brilliant and gifted young man born into a great destiny beyond his understanding, " +
                        "must travel to the most dangerous planet in the universe to ensure the future of his family and his people. " +
                        "As malevolent forces explode into conflict over the planet's exclusive supply of the most precious resource in existence-a " +
                        "commodity capable of unlocking humanity's greatest potential-only those who can conquer their fear will survive.",
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
                ),

            )
        )
    }
}