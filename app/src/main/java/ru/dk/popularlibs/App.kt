package ru.dk.popularlibs

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dk.popularlibs.domain.cache.RoomRepositoriesCache
import ru.dk.popularlibs.domain.cache.RoomUserCache
import ru.dk.popularlibs.data.retrofit.GitHubUsersRepoImpl
import ru.dk.popularlibs.data.retrofit.UsersGitHubAPI
import ru.dk.popularlibs.domain.room.UserDatabase
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cache.IRepositoriesCache
import ru.dk.popularlibs.domain.cache.IUserCache
import ru.dk.popularlibs.domain.cicerone.AndroidScreens
import ru.dk.popularlibs.domain.cicerone.CiceronePresenter
import ru.dk.popularlibs.domain.network.NetworkStatus
import ru.dk.popularlibs.ui.profile.ReposPresenter
import ru.dk.popularlibs.ui.users.UsersPresenter

class App : Application() {

    private val uiThread: Scheduler by lazy { AndroidSchedulers.mainThread() }
    private val ioThread: Scheduler by lazy { Schedulers.io() }
    private val baseUrl = "https://api.github.com/"
    private val cicerone by lazy { Cicerone.create() }
    private val router by lazy { cicerone.router }
    private val screens by lazy { AndroidScreens() }
    private val roomUserCache: IUserCache by lazy { RoomUserCache(getDB()) }
    private val roomRepositoriesCache: IRepositoriesCache by lazy { RoomRepositoriesCache(getDB()) }
    private val api by lazy { UsersGitHubAPI.create(baseUrl) }
    private val networkStatus by lazy { NetworkStatus(INSTANCE) }
    private val usersRepo: GithubUsersRepo by lazy {
        GitHubUsersRepoImpl(
            api, roomUserCache, roomRepositoriesCache, networkStatus, ioThread
        )
    }
    val userPresenter by lazy { UsersPresenter(usersRepo, uiThread) }
    val reposPresenter by lazy { ReposPresenter(usersRepo, uiThread) }
    val navigation by lazy { CiceronePresenter(router, screens) }
    val navigatorHolder by lazy { cicerone.getNavigatorHolder() }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        router.newRootScreen(screens.counter())
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set

        private const val DB_NAME = "database.db"
        private var dbUsers: UserDatabase? = null
        fun getDB(): UserDatabase {
            if (dbUsers == null) {
                synchronized(UserDatabase::class.java) {
                    if (dbUsers == null) {

                        dbUsers = Room.databaseBuilder(
                            INSTANCE, UserDatabase::class.java, DB_NAME
                        ).build()
                    }
                }
            }
            return dbUsers!!
        }
    }
}