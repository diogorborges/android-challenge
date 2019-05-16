package es.npatarino.android.gotchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.npatarino.android.gotchallenge.data.model.house.House
import io.reactivex.Single

@Dao
interface HousesDao {
    @Query("SELECT * from houses")
    fun getHouses(): Single<List<House>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHouses(vararg houses: House)
}
