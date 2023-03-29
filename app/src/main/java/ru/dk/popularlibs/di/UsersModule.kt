package ru.dk.popularlibs.di

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.dk.popularlibs.data.cache.RoomUserCache
import ru.dk.popularlibs.data.retrofit.GitHubUsersRepoImpl
import ru.dk.popularlibs.data.retrofit.UsersGitHubAPI
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.IUserCache
import ru.dk.popularlibs.domain.network.INetworkStatus
import ru.dk.popularlibs.domain.room.UserDatabase
import javax.inject.Named

@Module
open class UsersModule {

    @Provides
    fun roomUserCache(database: UserDatabase): IUserCache = RoomUserCache(database)

    @UserScope
    @Provides
    fun usersRepo(
        api: UsersGitHubAPI,
        roomUserCache: IUserCache,
        networkStatus: INetworkStatus,
        @Named("io") ioThread: Scheduler
    ): GithubUsersRepo = GitHubUsersRepoImpl(
        api, roomUserCache, networkStatus, ioThread
    )
}