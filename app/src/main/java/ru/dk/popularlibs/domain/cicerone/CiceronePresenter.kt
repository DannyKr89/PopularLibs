package ru.dk.popularlibs.domain.cicerone

import android.os.Bundle
import moxy.MvpPresenter
import moxy.MvpView
import ru.dk.popularlibs.App

class CiceronePresenter() : MvpPresenter<MvpView>() {

    private val screens: Screens = AndroidScreens()
    private val router = App.INSTANCE.router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.counter())
    }

    fun navigateToUsers() {
        router.navigateTo(screens.users())
    }

    fun navigateToProfile(bundle: Bundle) {
        router.navigateTo(screens.profile(bundle))
    }

    fun backClick() {
        router.exit()
    }
}