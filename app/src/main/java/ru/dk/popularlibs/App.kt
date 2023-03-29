package ru.dk.popularlibs

import android.app.Application
import ru.dk.popularlibs.di.*
import javax.inject.Inject

class App : Application() {
    @Inject
    lateinit var appComponent: AppComponent
    var usersSubcomponent: UsersSubcomponent? = null
    var repositorySubcomponent: RepositorySubcomponent? = null

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