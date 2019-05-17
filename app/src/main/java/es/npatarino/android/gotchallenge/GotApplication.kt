package es.npatarino.android.gotchallenge

import android.app.Application
import es.npatarino.android.gotchallenge.di.component.ApplicationComponent
import es.npatarino.android.gotchallenge.di.component.DaggerApplicationComponent
import es.npatarino.android.gotchallenge.di.module.RestModule
import es.npatarino.android.gotchallenge.di.module.RoomModule

class GotApplication : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        lateinit var instance: GotApplication
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        setupDagger()
    }

    private fun setupDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .restModule(RestModule(this))
            .roomModule(RoomModule())
            .build()

        applicationComponent.inject(this)
    }
}
