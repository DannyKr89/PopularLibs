package ru.dk.popularlibs

import android.view.View

interface CountersContract {

    interface CountersView {
        fun setText(view: View, text: String)
    }

    interface CountersPresenter {
        fun attach(countersView: CountersView)
        fun detach()
        fun counterClick(view: View, counter: Int)
    }
}