package es.npatarino.android.gotchallenge.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import es.npatarino.android.gotchallenge.data.model.Character
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CharactersDao {
    @Query("SELECT * from characters")
    fun getCharacters(): Single<List<Character>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacters(vararg characters: Character)

    @Query("SELECT * from characters WHERE name LIKE :query")
    fun getCharacterByQuery(query: String): Observable<List<Character>>

    @Query("SELECT * from characters WHERE houseId=:houseId")
    fun getCharactersHouse(houseId: String): Single<List<Character>>
}
