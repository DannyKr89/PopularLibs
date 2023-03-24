package ru.dk.popularlibs.domain.cicerone

import android.os.Bundle
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import moxy.MvpView

class CiceronePresenter(private val router: Router, private val screens: Screens) :
    MvpPresenter<MvpView>() {

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