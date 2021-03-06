package es.npatarino.android.gotchallenge.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House

@Database(entities = [Character::class, House::class], version = 1, exportSchema = false)
abstract class GotDatabase : RoomDatabase() {
    abstract fun charactersDao(): CharactersDao
    abstract fun housesDao(): HousesDao
}