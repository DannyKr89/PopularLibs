package ru.dk.popularlibs.domain

import io.reactivex.rxjava3.core.Single

interface GithubRepositoriesRepo {
    fun getUsersRepo(user: GithubUser): Single<List<GithubUserReposItem>>
}