package ru.dk.popularlibs.domain.cache

import ru.dk.popularlibs.domain.GithubUser

interface IUserCache {
    fun cacheUsers(users: List<GithubUser>)
    fun getCachedUsers(): List<GithubUser>
}