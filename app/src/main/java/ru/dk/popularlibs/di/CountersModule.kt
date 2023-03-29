package ru.dk.popularlibs.di

import dagger.Module
import dagger.Provides
import ru.dk.popularlibs.domain.CountersModel
import javax.inject.Singleton

@Module
class CountersModule {
    @Singleton
    @Provides
    fun countersModel(): CountersModel = CountersModel()
}