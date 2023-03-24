package ru.dk.popularlibs.data


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cache.IRepositoriesCache
import ru.dk.popularlibs.domain.cache.IUserCache
import ru.dk.popularlibs.domain.network.INetworkStatus
import ru.dk.popularlibs.domain.retrofit.UsersGitHubAPI

class GitHubUsersRepoImpl(
    private val api: UsersGitHubAPI,
    private val roomUserCache: IUserCache,
    private val roomRepositoriesCache: IRepositoriesCache,
    private val networkStatus: INetworkStatus
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
    }.subscribeOn(Schedulers.io())

    override fun getUsersRepo(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getReposUrl(url).flatMap { repos ->
                        Single.fromCallable {
                            roomRepositoriesCache.cacheRepositories(user, repos)
                            repos
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    roomRepositoriesCache.getCachedRepositories(user)
                }
            }
        }.subscribeOn(Schedulers.io())
}