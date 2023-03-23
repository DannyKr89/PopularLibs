package ru.dk.popularlibs.domain.cache

import ru.dk.popularlibs.App
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.room.RoomGithubUser

class RoomUserCache: IUserCache {

    private val db = App.getDB()

    override fun cacheUsers(users: List<GithubUser>) {
        val roomUsers = convertUsersToRoomUsers(users)
        db.userDao.insert(roomUsers)
    }

    override fun getCachedUsers(): List<GithubUser> {
       return convertRoomUsersToUsers()
    }

    private fun convertUsersToRoomUsers(users: List<GithubUser>): List<RoomGithubUser> {
        return users.map { user ->
            RoomGithubUser(
                id = user.id,
                login = user.login,
                avatarUrl = user.avatarUrl,
                reposUrl = user.reposUrl
            )
        }
    }

    private fun convertRoomUsersToUsers(): List<GithubUser> {
        return db.userDao.getAll().map { roomGithubUser ->
            GithubUser(
                login = roomGithubUser.login,
                id = roomGithubUser.id,
                avatarUrl = roomGithubUser.avatarUrl,
                reposUrl = roomGithubUser.reposUrl
            )
        }
    }
}