package ru.dk.popularlibs.domain

interface IUserCache {
    fun cacheUsers(users: List<GithubUser>)
    fun getCachedUsers(): List<GithubUser>
}