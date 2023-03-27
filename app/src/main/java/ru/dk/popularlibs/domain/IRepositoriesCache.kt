package ru.dk.popularlibs.domain

interface IRepositoriesCache {
    fun cacheRepositories(user: GithubUser, repositories: List<GithubUserReposItem>)
    fun getCachedRepositories(user: GithubUser): List<GithubUserReposItem>
}