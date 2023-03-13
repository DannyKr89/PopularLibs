package ru.dk.popularlibs.ui.users

import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUsersRepo

class UsersPresenter(val usersRepo: GithubUsersRepo) : MvpPresenter<UsersView>() {


    fun loadData() {
        viewState.showProgressbar(true)
        usersRepo.getUsers(onSuccess = {
            viewState.showUsers(it)
            viewState.showProgressbar(false)
        }, onError = {
            viewState.showError(it)
            viewState.showProgressbar(false)
        })
    }
}