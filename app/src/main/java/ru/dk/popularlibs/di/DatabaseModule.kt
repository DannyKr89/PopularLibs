package ru.dk.popularlibs.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.dk.popularlibs.App
import ru.dk.popularlibs.domain.network.INetworkStatus
import ru.dk.popularlibs.domain.network.NetworkStatus
import ru.dk.popularlibs.domain.room.UserDatabase
import javax.inject.Named
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun networkStatus(app: App): INetworkStatus = NetworkStatus(app)

    @Named("io")
    @Provides
    fun ioThread(): Scheduler = Schedulers.io()

    @Named("main")
    @Provides
    fun uiThread(): Scheduler = AndroidSchedulers.mainThread()

    private val DB_NAME = "database.db"

    @Singleton
    @Provides
    fun db(app: App): UserDatabase =
        Room.databaseBuilder(app, UserDatabase::class.java, DB_NAME).build()
}