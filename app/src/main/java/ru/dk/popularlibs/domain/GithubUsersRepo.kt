package ru.dk.popularlibs.domain

import io.reactivex.rxjava3.core.Single

interface GithubUsersRepo {
    fun getUsers(): Single<List<GithubUser>>
    fun getUsersRepo(user: GithubUser): Single<List<GithubUserReposItem>>
}