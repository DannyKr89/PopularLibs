package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUsersRepo
import javax.inject.Inject
import javax.inject.Named

class ReposPresenter() :
    MvpPresenter<ReposView>() {
    @Inject
    lateinit var usersRepo: GithubUsersRepo
    @Named("main")
    @Inject
    lateinit var uiThread: Scheduler

    @SuppressLint("CheckResult")
    fun loadData(user: GithubUser) {
        viewState.showProgressbar(true)
        usersRepo.getUsersRepo(user).observeOn(uiThread).subscribeBy(
            onSuccess = {
                viewState.showRepos(it)
                viewState.showProgressbar(false)
            }, onError = {
                viewState.showError(it)
                viewState.showProgressbar(false)
            })
    }
}