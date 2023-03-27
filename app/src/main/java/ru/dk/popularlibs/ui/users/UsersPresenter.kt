package ru.dk.popularlibs.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUsersRepo
import ru.dk.popularlibs.ui.cicerone.BackButtonListener
import ru.dk.popularlibs.ui.cicerone.Screens
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter() :
    MvpPresenter<UsersView>(), BackButtonListener {

    @Inject
    lateinit var usersRepo: GithubUsersRepo

    @Named("main")
    @Inject
    lateinit var uiThread: Scheduler

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens


    @SuppressLint("CheckResult")
    fun loadData() {
        viewState.showProgressbar(true)
        usersRepo.getUsers()
            .observeOn(uiThread)
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

    fun navigateToProfile(bundle: Bundle) {
        router.navigateTo(screens.profile(bundle))
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}