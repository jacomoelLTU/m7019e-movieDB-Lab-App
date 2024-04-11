package com.example.themoviedbv24.database

import com.example.themoviedbv24.model.Movie

class Movies {
    fun getMovies(): List<Movie> {
        return listOf<Movie> (
            Movie(1,
                "After the Pandemic",
                "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg",
                "/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                "2022-03-01",
                "Set in a post-apocalyptic world where a global airborne pandemic has wiped " +
                        "out 90% of the Earth's population and only the young and immune have endured " +
                        "as scavengers. For Ellie and Quinn, the daily challenges to stay alive are " +
                        "compounded when they become hunted by the merciless Stalkers."
                ),
            Movie(2,
                "Road House",
                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                "2024-03-08",
                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
                        "roadhouse, only to discover that this paradise is not all it seems."
            ),
            Movie(3,
                "Migration",
                "/ldfCF9RhR40mppkzmftxapaHeTo.jpg",
                "/2C3CdVzINUm5Cm1lrbT2uiRstwX.jpg",
                "2023-12-06",
                "After a migrating duck family alights on their pond with thrilling tales " +
                        "of far-flung places, the Mallard family embarks on a family road trip, " +
                        "from New England, to New York City, to tropical Jamaica.",
            ),
            Movie(4,
                "Hunters",
                "/3UKlVa1CBeQkRksHV5OfFTO52qd.jpg",
                "/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                "2021-09-13",
                 "As John T. Wrecker continues his task of protecting a group of refugees " +
                         "from a virus, the threat of something new and even more dangerous " +
                         "grows ever closer in the form of monstrous mutants.",
            ),
            Movie(5,
                "After the Pandemic",
                "/p1LbrdJ53dGfEhRopG71akfzOVu.jpg",
                "/pwGmXVKUgKN13psUjlhC9zBcq1o.jpg",
                "2022-03-01",
                "Set in a post-apocalyptic world where a global airborne pandemic has wiped " +
                        "out 90% of the Earth's population and only the young and immune have endured " +
                        "as scavengers. For Ellie and Quinn, the daily challenges to stay alive are " +
                        "compounded when they become hunted by the merciless Stalkers."
            ),
            Movie(6,
                "Road House",
                "/bXi6IQiQDHD00JFio5ZSZOeRSBh.jpg",
                "/9c0lHTXRqDBxeOToVzRu0GArSne.jpg",
                "2024-03-08",
                "Ex-UFC fighter Dalton takes a job as a bouncer at a Florida Keys " +
                        "roadhouse, only to discover that this paradise is not all it seems."
            ),
            Movie(7,
                "Migration",
                "/ldfCF9RhR40mppkzmftxapaHeTo.jpg",
                "/2C3CdVzINUm5Cm1lrbT2uiRstwX.jpg",
                "2023-12-06",
                "After a migrating duck family alights on their pond with thrilling tales " +
                        "of far-flung places, the Mallard family embarks on a family road trip, " +
                        "from New England, to New York City, to tropical Jamaica.",
            ),
            Movie(8,
                "Hunters",
                "/3UKlVa1CBeQkRksHV5OfFTO52qd.jpg",
                "/xOMo8BRK7PfcJv9JCnx7s5hj0PX.jpg",
                "2021-09-13",
                "As John T. Wrecker continues his task of protecting a group of refugees " +
                        "from a virus, the threat of something new and even more dangerous " +
                        "grows ever closer in the form of monstrous mutants.",
            )
        )
    }
}