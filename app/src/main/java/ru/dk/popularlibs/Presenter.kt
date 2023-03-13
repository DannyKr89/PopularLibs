package ru.dk.popularlibs

import moxy.MvpPresenter

class Presenter() : MvpPresenter<CountersView>() {

    val model = CountersModel()

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


}