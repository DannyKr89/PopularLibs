package ru.dk.popularlibs.data

import io.reactivex.rxjava3.core.Single
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUsersRepo

class OurUsersRepoImpl : GithubUsersRepo {
    private val data: List<GithubUser> = listOf(
        GithubUser("mojombo", 1, "https://avatars.githubusercontent.com/u/1?v=4"),
        GithubUser("defunkt", 2, "https://avatars.githubusercontent.com/u/2?v=4"),
        GithubUser("pjhyett", 3, "https://avatars.githubusercontent.com/u/3?v=4"),
        GithubUser("wycats", 4, "https://avatars.githubusercontent.com/u/4?v=4"),
        GithubUser("ezmobius", 5, "https://avatars.githubusercontent.com/u/5?v=4"),
        GithubUser("ivey", 6, "https://avatars.githubusercontent.com/u/6?v=4"),
        GithubUser("evanphx", 7, "https://avatars.githubusercontent.com/u/7?v=4"),
        GithubUser("vanpelt", 8, "https://avatars.githubusercontent.com/u/17?v=4"),
        GithubUser("wayneeseguin", 9, "https://avatars.githubusercontent.com/u/18?v=4"),
        GithubUser("brynary", 10, "https://avatars.githubusercontent.com/u/19?v=4"),
        GithubUser("kevinclark", 11, "https://avatars.githubusercontent.com/u/20?v=4"),
    )

    override fun getUsers() = Single.just(data)
}

