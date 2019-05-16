package es.npatarino.android.gotchallenge.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import es.npatarino.android.gotchallenge.data.local.CharactersDao
import es.npatarino.android.gotchallenge.data.local.GotDatabase
import es.npatarino.android.gotchallenge.data.local.HousesDao
import javax.inject.Singleton

@Module
class RoomModule() {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): GotDatabase =
        Room.databaseBuilder(context, GotDatabase::class.java, "Got.db").build()

    @Provides
    @Singleton
    fun provideCharactersDao(database: GotDatabase): CharactersDao = database.charactersDao()

    @Provides
    @Singleton
    fun provideHousesDao(database: GotDatabase): HousesDao = database.housesDao()
}