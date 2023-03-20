package ru.dk.popularlibs.ui.profile

import android.annotation.SuppressLint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUsersRepo

class ReposPresenter(private val usersRepo: GithubUsersRepo) : MvpPresenter<ReposView>() {

    @SuppressLint("CheckResult")
    fun loadData(url: String) {
        viewState.showProgressbar(true)
        usersRepo.getUsersRepo(url)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = {
                    viewState.showRepos(it)
                    viewState.showProgressbar(false)
                },
                onError = {
                    viewState.showError(it)
                    viewState.showRepos(listOf())
                    viewState.showProgressbar(false)
                }
            )
    }
}