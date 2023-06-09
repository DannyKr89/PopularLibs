package ru.dk.popularlibs.ui

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.MvpView
import moxy.ktx.moxyPresenter
import ru.dk.popularlibs.App
import ru.dk.popularlibs.R
import ru.dk.popularlibs.domain.cicerone.BackButtonListener
import ru.dk.popularlibs.domain.cicerone.CiceronePresenter

class MainActivity : MvpAppCompatActivity(), MvpView {
    private val navigator = AppNavigator(this, R.id.mainContainer)
    private val presenter by moxyPresenter { CiceronePresenter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        App.INSTANCE.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        App.INSTANCE.navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }
        presenter.backClick()
    }
}