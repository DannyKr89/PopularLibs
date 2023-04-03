package ru.dk.popularlibs.ui.users

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import ru.dk.popularlibs.domain.GithubUser

@StateStrategyType(AddToEndSingleStrategy::class)
interface UsersView : MvpView {
    fun showProgressbar(inProgress: Boolean)
    fun showUsers(users: List<GithubUser>)
    fun showError(throwable: Throwable)
}