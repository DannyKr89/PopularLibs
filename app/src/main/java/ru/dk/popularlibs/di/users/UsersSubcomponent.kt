package ru.dk.popularlibs.di.users

import dagger.Subcomponent
import ru.dk.popularlibs.ui.users.UsersPresenter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class UserScope

@UserScope
@Subcomponent(
    modules = [UsersModule::class]
)
interface UsersSubcomponent {
    fun inject(usersPresenter: UsersPresenter)
}