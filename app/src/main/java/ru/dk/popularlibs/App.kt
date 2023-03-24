package ru.dk.popularlibs

import android.app.Application
import ru.dk.popularlibs.di.AppComponent
import ru.dk.popularlibs.di.AppModule
import ru.dk.popularlibs.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var appComponent: AppComponent
//    private val uiThread: Scheduler by lazy { AndroidSchedulers.mainThread() }

//    val userPresenter by lazy { UsersPresenter(usersRepo, uiThread) }
//    val reposPresenter by lazy { ReposPresenter(usersRepo, uiThread) }
//    val navigation by lazy { CiceronePresenter(router, screens) }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set


    }
}