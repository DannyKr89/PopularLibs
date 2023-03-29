package ru.dk.popularlibs.di

import dagger.Module
import dagger.Provides
import ru.dk.popularlibs.App
import javax.inject.Singleton

@Module
class AppModule(val app: App) {

    @Singleton
    @Provides
    fun app(): App = app
}