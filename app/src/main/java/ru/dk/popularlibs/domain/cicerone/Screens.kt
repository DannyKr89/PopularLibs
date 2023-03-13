package ru.dk.popularlibs.domain.cicerone

import com.github.terrakok.cicerone.Screen

interface Screens {
    fun counter(): Screen
    fun users(): Screen
}