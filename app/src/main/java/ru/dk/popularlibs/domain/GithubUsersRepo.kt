package ru.dk.popularlibs.domain

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUsersRepo(url: String): Single<List<GithubUserReposItem>>
}