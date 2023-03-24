package ru.dk.popularlibs.di

import dagger.Module
import dagger.Provides
import ru.dk.popularlibs.data.retrofit.UsersGitHubAPI
import javax.inject.Named

@Module
class ApiModule {

    @Named("baseUrl")
    @Provides
    fun baseUrl(): String = "https://api.github.com/"

    @Provides
    fun api(@Named("baseUrl") baseUrl: String): UsersGitHubAPI = UsersGitHubAPI.create(baseUrl)
}