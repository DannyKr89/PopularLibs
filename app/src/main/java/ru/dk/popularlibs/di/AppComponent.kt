package ru.dk.popularlibs.di

import androidx.fragment.app.Fragment
import dagger.Component
import ru.dk.popularlibs.ui.MainActivity
import ru.dk.popularlibs.ui.cicerone.CiceronePresenter
import ru.dk.popularlibs.ui.counters.CounterPresenter
import ru.dk.popularlibs.ui.profile.ReposPresenter
import ru.dk.popularlibs.ui.users.UsersPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        ApiModule::class,
        RepoModule::class,
        CountersModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(fragment: Fragment)
    fun inject(usersPresenter: UsersPresenter)
    fun inject(reposPresenter: ReposPresenter)
    fun inject(ciceronePresenter: CiceronePresenter)
    fun inject(counterPresenter: CounterPresenter)
}