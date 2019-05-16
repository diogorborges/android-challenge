package es.npatarino.android.gotchallenge.data.local

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GotApiLocalDataSource @Inject constructor(
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

    fun searchQuery(query: String): Observable<ArrayList<Character>> =
        charactersDao.searchQuery(query).map { ArrayList(it) }

    fun getCharactersHouse(): Single<ArrayList<Character>> =
        charactersDao.getCharactersHouse().map { ArrayList(it) }
}