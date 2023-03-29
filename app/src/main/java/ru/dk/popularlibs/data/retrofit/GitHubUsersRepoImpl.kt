package ru.dk.popularlibs.data.retrofit


import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.IUserCache
import ru.dk.popularlibs.domain.network.INetworkStatus

class GitHubUsersRepoImpl(
    private val api: UsersGitHubAPI,
    private val roomUserCache: IUserCache,
    private val networkStatus: INetworkStatus,
    private val ioThread: Scheduler
) : GithubUsersRepo {

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getListUsers().flatMap { users ->
                Single.fromCallable {
                    roomUserCache.cacheUsers(users)
                    users
                }
            }
        } else {
            Single.fromCallable {
                roomUserCache.getCachedUsers()
            }
        }
    }.subscribeOn(ioThread)
}