package ru.dk.popularlibs

import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle

interface CountersView : MvpView {
    @AddToEndSingle
    fun setTextFirstCounter(text: String)

    @AddToEndSingle
    fun setTextSecondCounter(text: String)

    @AddToEndSingle
    fun setTextThirdCounter(text: String)
}