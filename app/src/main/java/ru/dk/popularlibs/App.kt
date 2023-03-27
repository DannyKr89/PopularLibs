package ru.dk.popularlibs

import android.app.Application
import ru.dk.popularlibs.di.AppComponent
import ru.dk.popularlibs.di.AppModule
import ru.dk.popularlibs.di.DaggerAppComponent
import ru.dk.popularlibs.di.repository.RepositorySubcomponent
import ru.dk.popularlibs.di.users.UsersSubcomponent
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var appComponent: AppComponent
    private var usersSubcomponent: UsersSubcomponent? = null
    private var repositorySubcomponent: RepositorySubcomponent? = null

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initUsersSubcomponent() = appComponent.usersSubcomponent().also {
        usersSubcomponent = it
    }

    fun initRepositoriesSubcomponent() = appComponent.repositoriesSubcomponent().also {
        repositorySubcomponent = it
    }

    fun endUserScope() {
        usersSubcomponent = null
    }

    fun endRepositoriesScope() {
        repositorySubcomponent = null
    }

    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}