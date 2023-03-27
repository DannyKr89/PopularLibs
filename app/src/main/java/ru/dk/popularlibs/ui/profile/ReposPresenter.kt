package ru.dk.popularlibs.ui.profile

import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.GithubRepositoriesRepo
import ru.dk.popularlibs.domain.GithubUser
import ru.dk.popularlibs.ui.cicerone.BackButtonListener
import javax.inject.Inject
import javax.inject.Named

class ReposPresenter() :
    MvpPresenter<ReposView>(), BackButtonListener {
    @Inject
    lateinit var repositoriesRepo: GithubRepositoriesRepo

    @Named("main")
    @Inject
    lateinit var uiThread: Scheduler

    @Inject
    lateinit var router: Router

    private var repos: Disposable? = null

    fun loadData(user: GithubUser) {
        viewState.showProgressbar(true)
        repos = repositoriesRepo.getUsersRepo(user).observeOn(uiThread).subscribeBy(
            onSuccess = {
                viewState.showRepos(it)
                viewState.showProgressbar(false)
            }, onError = {
                viewState.showError(it)
                viewState.showProgressbar(false)
            })
    }

    override fun backPressed(): Boolean {
        router.exit()
        repos?.dispose()
        return true
    }
}