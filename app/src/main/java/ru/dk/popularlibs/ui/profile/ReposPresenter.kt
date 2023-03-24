package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.domain.GithubUsersRepo

class ReposPresenter(private val usersRepo: GithubUsersRepo) : MvpPresenter<ReposView>() {

    @SuppressLint("CheckResult")
    fun loadData(user: GithubUser) {
        viewState.showProgressbar(true)
        usersRepo.getUsersRepo(user)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    viewState.showRepos(it)
                    viewState.showProgressbar(false)
                },
                onError = {
                    viewState.showError(it)
                    viewState.showProgressbar(false)
                }
            )
    }
}