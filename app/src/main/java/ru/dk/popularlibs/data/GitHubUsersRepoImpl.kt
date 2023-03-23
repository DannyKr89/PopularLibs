package ru.dk.popularlibs.data


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cache.IRepositoriesCache
import ru.dk.popularlibs.domain.cache.IUserCache
import ru.dk.popularlibs.domain.network.NetworkStatus
import ru.dk.popularlibs.domain.retrofit.UsersGitHubAPI

class GitHubUsersRepoImpl(
    private val roomUserCache: IUserCache,
    private val roomRepositoriesCache: IRepositoriesCache
) : GithubUsersRepo {
    private val networkStatus = NetworkStatus()

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofit.create(UsersGitHubAPI::class.java)

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