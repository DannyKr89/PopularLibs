package ru.dk.popularlibs

import android.app.Application
import androidx.room.Room
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.dk.popularlibs.data.GitHubUsersRepoImpl
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cicerone.CiceronePresenter
import ru.dk.popularlibs.domain.room.UserDatabase

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val navigation get() = CiceronePresenter()

    val usersRepo: GithubUsersRepo by lazy { GitHubUsersRepoImpl() }

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
                            INSTANCE.applicationContext,
                            UserDatabase::class.java,
                            DB_NAME
                        )
                            .build()
                    }
                }
            }
            return dbUsers!!
        }
    }
}