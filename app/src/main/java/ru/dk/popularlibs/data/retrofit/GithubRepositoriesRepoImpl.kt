package ru.dk.popularlibs.data.retrofit

import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import ru.dk.popularlibs.domain.GithubRepositoriesRepo
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.IRepositoriesCache
import ru.dk.popularlibs.domain.network.INetworkStatus

class GithubRepositoriesRepoImpl(
    private val api: UsersGitHubAPI,
    private val networkStatus: INetworkStatus,
    private val cache: IRepositoriesCache,
    private val ioThread: Scheduler
) : GithubRepositoriesRepo {

    override fun getUsersRepo(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getReposUrl(url).flatMap { repos ->
                        Single.fromCallable {
                            cache.cacheRepositories(user, repos)
                            repos
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    cache.getCachedRepositories(user)
                }
            }
        }.subscribeOn(ioThread)
}