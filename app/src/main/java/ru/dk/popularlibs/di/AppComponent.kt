package ru.dk.popularlibs.di

import androidx.fragment.app.Fragment
import dagger.Component
import ru.dk.popularlibs.di.repository.RepositorySubcomponent
import ru.dk.popularlibs.di.users.UsersSubcomponent
import ru.dk.popularlibs.ui.MainActivity
import ru.dk.popularlibs.ui.cicerone.CiceronePresenter
import ru.dk.popularlibs.ui.counters.CounterPresenter
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        CiceroneModule::class,
        ApiModule::class,
        DatabaseModule::class,
        CountersModule::class,
        AppModule::class
    ]
)
interface AppComponent {

    fun usersSubcomponent(): UsersSubcomponent
    fun repositoriesSubcomponent(): RepositorySubcomponent
    fun inject(mainActivity: MainActivity)
    fun inject(fragment: Fragment)
    fun inject(ciceronePresenter: CiceronePresenter)
    fun inject(counterPresenter: CounterPresenter)
}