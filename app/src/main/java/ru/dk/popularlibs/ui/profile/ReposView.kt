package ru.dk.popularlibs.ui.profile

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dk.popularlibs.domain.GithubUserReposItem

@StateStrategyType(AddToEndSingleStrategy::class)
interface ReposView : MvpView {
    fun showProgressbar(inProgress: Boolean)
    fun showRepos(reposItem: List<GithubUserReposItem>)
    fun showError(throwable: Throwable)
}