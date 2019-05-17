package es.npatarino.android.gotchallenge.data.local

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GotLocalDataSource @Inject constructor(
    private val charactersDao: CharactersDao,
    private val housesDao: HousesDao
) {
    fun getCharacters(): Single<ArrayList<Character>> =
        charactersDao.getCharacters().map { ArrayList(it) }

    fun insertCharacters(vararg characters: Character): Completable =
        Completable.fromAction { charactersDao.insertCharacters(*characters) }

    fun getHouses(): Single<ArrayList<House>> =
        housesDao.getHouses().map { ArrayList(it) }

    fun insertHouses(vararg house: House): Completable =
        Completable.fromAction { housesDao.insertHouses(*house) }

    fun getCharacterByQuery(query: String): Observable<ArrayList<Character>> =
        charactersDao.getCharacterByQuery(query).map { ArrayList(it) }

    fun getCharactersHouse(houseId: String): Single<ArrayList<Character>> =
        charactersDao.getCharactersHouse(houseId).map { ArrayList(it) }
}