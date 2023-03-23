package ru.dk.popularlibs.domain.cache

import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem

interface IRepositoriesCache {
    fun cacheRepositories(user: GithubUser, repositories: List<GithubUserReposItem>)
    fun getCachedRepositories(user: GithubUser): List<GithubUserReposItem>
}