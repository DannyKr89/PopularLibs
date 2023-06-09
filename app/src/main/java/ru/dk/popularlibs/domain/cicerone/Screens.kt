package ru.dk.popularlibs.domain.cicerone

import android.os.Bundle
import com.github.terrakok.cicerone.Screen

interface Screens {
    fun counter(): Screen
    fun users(): Screen
    fun profile(bundle: Bundle): Screen
}