package ru.dk.popularlibs.ui.users

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubUser
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

    private var users: Disposable? = null


    fun loadData() {
        viewState.showProgressbar(true)
        users = usersRepo.getUsers()
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

    fun navigateToProfile(githubUser: GithubUser) {
        router.navigateTo(screens.profile(githubUser))
    }

    override fun backPressed(): Boolean {
        router.exit()
        users?.dispose()
        return true
    }

    override fun backPressed(): Boolean {
        router.exit()
        return true
    }
}