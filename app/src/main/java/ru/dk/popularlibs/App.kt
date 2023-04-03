package ru.dk.popularlibs

import android.app.Application
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import ru.dk.popularlibs.data.OurUsersRepoImpl
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.cicerone.CiceronePresenter

class App : Application() {

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val router get() = cicerone.router
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val navigation get() = CiceronePresenter()

    val usersRepo: GithubUsersRepo by lazy { OurUsersRepoImpl() }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}