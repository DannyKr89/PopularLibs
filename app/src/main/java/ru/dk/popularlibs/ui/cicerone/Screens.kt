package ru.dk.popularlibs.ui.cicerone

import com.github.terrakok.cicerone.Screen
import ru.dk.popularlibs.domain.GithubUser

interface Screens {
    fun counter(): Screen
    fun users(): Screen
    fun profile(githubUser: GithubUser): Screen
}