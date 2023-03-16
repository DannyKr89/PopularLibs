package ru.dk.popularlibs.ui.users

import android.annotation.SuppressLint
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUsersRepo

class UsersPresenter(private val usersRepo: GithubUsersRepo) : MvpPresenter<UsersView>() {


    @SuppressLint("CheckResult")
    fun loadData() {
        viewState.showProgressbar(true)
        usersRepo.getUsers()
            .subscribeBy(
                onSuccess = {
                    viewState.showUsers(it)
                    viewState.showProgressbar(false)
                },
                onError = {
                    viewState.showError(it)
                    viewState.showProgressbar(false)
                }
            )
    }
}