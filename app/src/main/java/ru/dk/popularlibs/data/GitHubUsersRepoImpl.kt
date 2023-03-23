package ru.dk.popularlibs.data


import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.popularlibs.App
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.network.NetworkStatus
import ru.dk.popularlibs.domain.retrofit.UsersGitHubAPI
import ru.dk.popularlibs.domain.room.RoomGithubRepository
import ru.dk.popularlibs.domain.room.RoomGithubUser

class GitHubUsersRepoImpl : GithubUsersRepo {
    private val db = App.getDB()
    private val networkStatus = NetworkStatus()

    private val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create()).build()
    private val api = retrofit.create(UsersGitHubAPI::class.java)

    override fun getUsers() = networkStatus.isOnlineSingle().flatMap { isOnline ->
        if (isOnline) {
            api.getListUsers().flatMap { users ->
                Single.fromCallable {
                    val roomUsers = users.map { user ->
                        RoomGithubUser(
                            id = user.id,
                            login = user.login,
                            avatarUrl = user.avatarUrl,
                            reposUrl = user.reposUrl
                        )
                    }
                    db.userDao.insert(roomUsers)
                    users
                }
            }
        } else {
            Single.fromCallable {
                db.userDao.getAll().map { roomGithubUser ->
                    GithubUser(
                        login = roomGithubUser.login,
                        id = roomGithubUser.id,
                        avatarUrl = roomGithubUser.avatarUrl,
                        reposUrl = roomGithubUser.reposUrl
                    )
                }
            }
        }
    }.subscribeOn(Schedulers.io())

    override fun getUsersRepo(user: GithubUser) =
        networkStatus.isOnlineSingle().flatMap { isOnline ->
            if (isOnline) {
                user.reposUrl.let { url ->
                    api.getReposUrl(url).flatMap { repos ->
                        Single.fromCallable {
                            val roomUser = user.login.let {
                                db.userDao.findByLogin(it)
                            } ?: throw java.lang.RuntimeException("NO SUCH USER IN CACHE")
                            val roomGithubRepository = repos.map {
                                RoomGithubRepository(
                                    userId = roomUser.id ?: 0,
                                    createdAt = it.createdAt ?: "",
                                    defaultBranch = it.defaultBranch ?: "",
                                    description = it.description ?: "",
                                    forks = it.forks ?: 0,
                                    homepage = it.homepage ?: "",
                                    htmlUrl = it.htmlUrl ?: "",
                                    language = it.language ?: "",
                                    name = it.name ?: "",
                                    size = it.size ?: 0,
                                    updatedAt = it.updatedAt ?: "",
                                    visibility = it.visibility ?: "",
                                    watchers = it.watchers ?: 0,
                                    id = it.id
                                )
                            }
                            db.repositoryDao.insert(roomGithubRepository)
                            repos
                        }
                    }
                }
            } else {
                Single.fromCallable {
                    val roomUser = user.login.let { db.userDao.findByLogin(it) }
                        ?: throw java.lang.RuntimeException("NO SUCH USER IN CACHE")
                    db.repositoryDao.findById(roomUser.id).map {
                        GithubUserReposItem(
                            createdAt = it.createdAt,
                            defaultBranch = it.defaultBranch,
                            description = it.description,
                            forks = it.forks,
                            homepage = it.homepage,
                            htmlUrl = it.htmlUrl,
                            language = it.language,
                            name = it.name,
                            size = it.size,
                            updatedAt = it.updatedAt,
                            visibility = it.visibility,
                            watchers = it.watchers,
                            id = it.id
                        )
                    }
                }
            }
        }.subscribeOn(Schedulers.io())
}