package ru.dk.popularlibs.domain.cicerone

import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dk.popularlibs.ui.counters.CounterFragment
import ru.dk.popularlibs.ui.users.UsersFragment

class AndroidScreens : Screens {
    override fun counter() = FragmentScreen { CounterFragment.newInstance() }
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
}