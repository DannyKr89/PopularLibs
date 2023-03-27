package ru.dk.popularlibs.di.repository

import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.core.Scheduler
import ru.dk.popularlibs.data.cache.RoomRepositoriesCache
import ru.dk.popularlibs.data.retrofit.GithubRepositoriesRepoImpl
import ru.dk.popularlibs.data.retrofit.UsersGitHubAPI
import ru.dk.popularlibs.domain.GithubRepositoriesRepo
import ru.dk.popularlibs.domain.IRepositoriesCache
import ru.dk.popularlibs.domain.network.INetworkStatus
import ru.dk.popularlibs.domain.room.UserDatabase
import javax.inject.Named

@Module
class RepositoryModule {

    @Provides
    fun roomRepositoriesCache(database: UserDatabase): IRepositoriesCache =
        RoomRepositoriesCache(database)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: UsersGitHubAPI,
        networkStatus: INetworkStatus,
        cache: IRepositoriesCache,
        @Named("io") ioThread: Scheduler
    ): GithubRepositoriesRepo = GithubRepositoriesRepoImpl(api, networkStatus, cache, ioThread)
}