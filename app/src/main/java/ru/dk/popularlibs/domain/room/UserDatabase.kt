package ru.dk.popularlibs.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao
}