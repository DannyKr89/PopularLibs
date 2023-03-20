package ru.dk.popularlibs.domain.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem

interface UsersGitHubAPI {
    @GET("users")
    fun getListUsers(): Single<List<GithubUser>>

    @GET
    fun getReposUrl(@Url url: String): Single<List<GithubUserReposItem>>
}