package ru.dk.popularlibs.domain.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUserReposItem

interface UsersGitHubAPI {
    @GET("users")
    fun getListUsers(): Single<List<GithubUser>>

    @GET
    fun getReposUrl(@Url url: String): Single<List<GithubUserReposItem>>

    companion object {
        fun create(): UsersGitHubAPI {
            val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(UsersGitHubAPI::class.java)
        }
    }
}