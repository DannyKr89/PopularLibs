package ru.dk.popularlibs

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.dk.popularlibs.data.GitHubUsersRepoImpl
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cache.IRepositoriesCache
import ru.dk.popularlibs.domain.cache.IUserCache
import ru.dk.popularlibs.domain.cache.RoomRepositoriesCache
import ru.dk.popularlibs.domain.cache.RoomUserCache
import ru.dk.popularlibs.domain.cicerone.CiceronePresenter
import ru.dk.popularlibs.domain.network.NetworkStatus
import ru.dk.popularlibs.domain.retrofit.UsersGitHubAPI
import ru.dk.popularlibs.domain.room.UserDatabase

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val navigation get() = CiceronePresenter()
    private val roomUserCache: IUserCache by lazy { RoomUserCache() }
    private val roomRepositoriesCache: IRepositoriesCache by lazy { RoomRepositoriesCache() }
    private val api = UsersGitHubAPI.create()
    private val networkStatus = NetworkStatus()

    val usersRepo: GithubUsersRepo by lazy {
        GitHubUsersRepoImpl(
            api, roomUserCache, roomRepositoriesCache, networkStatus
        )
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
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
                            INSTANCE.applicationContext, UserDatabase::class.java, DB_NAME
                        ).build()
                    }
                }
            }
            return dbUsers!!
        }
    }
}