package ru.dk.popularlibs.domain

interface GithubUsersRepo {
    fun getUsers(onSuccess: (List<GithubUser>) -> Unit, onError: ((Throwable) -> Unit)?)
}