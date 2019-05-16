package es.npatarino.android.gotchallenge.di.component

import dagger.Component
import es.npatarino.android.gotchallenge.GotApplication
import es.npatarino.android.gotchallenge.data.remote.GotService
import es.npatarino.android.gotchallenge.di.module.RestModule
import es.npatarino.android.gotchallenge.di.module.RoomModule
import es.npatarino.android.gotchallenge.presentation.MainActivity
import es.npatarino.android.gotchallenge.presentation.characterdetail.CharacterDetailFragment
import es.npatarino.android.gotchallenge.presentation.characters.CharactersFragment
import es.npatarino.android.gotchallenge.presentation.charactershouse.CharactersHouseFragment
import es.npatarino.android.gotchallenge.presentation.houses.HousesFragment
import javax.inject.Singleton

@Singleton
@Component(modules = [RestModule::class, RoomModule::class])
interface ApplicationComponent {

    fun apiService(): GotService

    fun inject(application: GotApplication)

    fun inject(activity: MainActivity)

    fun inject(fragment: CharactersFragment)

    fun inject(fragment: HousesFragment)

    fun inject(fragment: CharactersHouseFragment)

    fun inject(fragment: CharacterDetailFragment)
}