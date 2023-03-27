package ru.dk.popularlibs.di

import dagger.Subcomponent
import ru.dk.popularlibs.ui.profile.ReposPresenter
import javax.inject.Scope

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class RepositoryScope

@RepositoryScope
@Subcomponent(
    modules = [RepositoryModule::class]
)
interface RepositorySubcomponent {
    fun inject(reposPresenter: ReposPresenter)
}