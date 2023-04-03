package ru.dk.popularlibs.domain.cicerone

import android.os.Bundle
import com.github.terrakok.cicerone.androidx.FragmentScreen
import ru.dk.popularlibs.ui.counters.CounterFragment
import ru.dk.popularlibs.ui.profile.ProfileFragment
import ru.dk.popularlibs.ui.users.UsersFragment

class AndroidScreens : Screens {
    override fun counter() = FragmentScreen { CounterFragment.newInstance() }
    override fun users() = FragmentScreen { UsersFragment.newInstance() }
    override fun profile(bundle: Bundle) = FragmentScreen { ProfileFragment.newInstance(bundle) }
}