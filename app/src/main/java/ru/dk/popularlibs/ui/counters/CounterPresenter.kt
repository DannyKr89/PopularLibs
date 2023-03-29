package ru.dk.popularlibs.ui.counters

import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import ru.dk.popularlibs.domain.CountersModel
import ru.dk.popularlibs.ui.cicerone.Screens
import javax.inject.Inject

class CounterPresenter() : MvpPresenter<CountersView>() {

    @Inject
    lateinit var model: CountersModel

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: Screens

    fun getFirstCounter() = model.getCounter(0)
    fun getSecondCounter() = model.getCounter(1)
    fun getThirdCounter() = model.getCounter(2)


    fun firstCounterClick() {
        val nextValue = model.increaseCounter(0)
        viewState.setTextFirstCounter(nextValue.toString())
    }

    fun secondCounterClick() {
        val nextValue = model.increaseCounter(1)
        viewState.setTextSecondCounter(nextValue.toString())
    }

    fun thirdCounterClick() {
        val nextValue = model.increaseCounter(2)
        viewState.setTextThirdCounter(nextValue.toString())
    }

    fun navigateToUsers() {
        router.navigateTo(screens.users())
    }


}