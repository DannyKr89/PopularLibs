package ru.dk.popularlibs.ui.cicerone

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView
import javax.inject.Inject

class CiceronePresenter() :
    MvpPresenter<MvpView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.newRootScreen(screens.counter())
    }


    fun backClick(): Boolean {
        router.exit()
        return true
    }
}