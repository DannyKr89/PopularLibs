package ru.dk.popularlibs.domain.cache

import ru.dk.popularlibs.App
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem
import ru.dk.popularlibs.domain.room.RoomGithubRepository
import ru.dk.popularlibs.domain.room.RoomGithubUser

class RoomRepositoriesCache : IRepositoriesCache {

    private val db = App.getDB()

    override fun cacheRepositories(user: GithubUser, repositories: List<GithubUserReposItem>) {
        val roomUser = user.login.let {
            db.userDao.findByLogin(it)
        } ?: throw java.lang.RuntimeException("NO SUCH USER IN CACHE")
        val roomGithubRepository = convertRepositoriesToRoomRepositories(roomUser, repositories)
        db.repositoryDao.insert(roomGithubRepository)
    }

    override fun getCachedRepositories(user: GithubUser): List<GithubUserReposItem> {
        val roomUser = user.login.let { db.userDao.findByLogin(it) }
            ?: throw java.lang.RuntimeException("NO SUCH USER IN CACHE")
        return convertRoomRepositoryToRepository(roomUser)
    }

    private fun convertRepositoriesToRoomRepositories(
        roomUser: RoomGithubUser,
        repositories: List<GithubUserReposItem>
    ): List<RoomGithubRepository> {
        return repositories.map {
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
    }

    private fun convertRoomRepositoryToRepository(roomUser: RoomGithubUser): List<GithubUserReposItem> {
        return db.repositoryDao.findById(roomUser.id).map {
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