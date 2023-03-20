package ru.dk.popularlibs.data


import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.domain.retrofit.UsersGitHubAPI

class GitHubUsersRepoImpl : GithubUsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val api = retrofit.create(UsersGitHubAPI::class.java)

    override fun getUsers() = api.getListUsers().subscribeOn(Schedulers.io())
    override fun getUsersRepo(url: String) = api.getReposUrl(url).subscribeOn(Schedulers.io())
}