package ru.dk.popularlibs

import android.view.View

class Presenter() : CountersContract.CountersPresenter {

    private var _countersView: CountersContract.CountersView? = null

    val model = CountersModel()

    override fun attach(countersView: CountersContract.CountersView) {
        _countersView = countersView
    }

    override fun detach() {
        _countersView = null
    }

    override fun counterClick(view: View, counter: Int) {
        val nextValue = model.increaseCounter(counter)
        _countersView?.setText(view, nextValue.toString())
    }

}