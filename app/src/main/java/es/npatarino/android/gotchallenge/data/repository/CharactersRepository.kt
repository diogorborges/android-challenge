package es.npatarino.android.gotchallenge.data.repository

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import es.npatarino.android.gotchallenge.common.ListUtils
import es.npatarino.android.gotchallenge.common.hasNetwork
import es.npatarino.android.gotchallenge.data.NetworkException
import es.npatarino.android.gotchallenge.data.local.GotApiLocalDataSource
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.data.remote.GotRemoteDataSource
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CharactersRepository @Inject constructor(
    private val gotRemoteDataSource: GotRemoteDataSource,
    private val gotLocalDataSource: GotApiLocalDataSource,
    private val context: Context
) {

    companion object {
        private const val TAG = "CharactersRepository"
    }

    fun getCharacters(): Single<ArrayList<Character>> =
        gotLocalDataSource.getCharacters()
            .flatMap {
                if (it.isNotEmpty()) {
                    Log.i(TAG, "Dispatching ${it.size} characters from DB...")
                    return@flatMap Single.just(it)
                } else {
                    return@flatMap fetchAndPersistCharacters()
                }
            }

    @SuppressLint("CheckResult")
    private fun fetchAndPersistCharacters(): Single<ArrayList<Character>> =
        when (hasNetwork(context)) {
            true -> {
                gotRemoteDataSource.getCharacters()
                    .doOnSuccess {
                        val houseList = createHouseList(it)
                        persistHouses(houseList)
                    }
                    .doOnSuccess {
                        persistCharacters(it)
                    }
                    .doOnError {
                        Log.i(TAG, "Error ${it.message}")
                    }
            }
            false -> Single.error(NetworkException())
        }

    private fun createHouseList(characterList: ArrayList<Character>): ArrayList<House> {
        val houseIdList: MutableSet<String> = mutableSetOf()
        val houseList: ArrayList<House> = arrayListOf()
        var house: House? = null

        characterList.forEach { if (it.houseId.isNotEmpty()) houseIdList.add(it.houseId) }

        houseIdList.forEach { houseId ->
            characterList.forEach {
                if (it.houseId.contains(houseId)) house =
                    House(
                        it.houseId,
                        it.houseImageUrl,
                        it.houseName
                    )
            }
            house?.let { houseList.add(it) }
        }
        return houseList
    }

    private fun persistHouses(houses: ArrayList<House>) =
        insertHouses(*ListUtils.toArray(House::class.java, houses))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.i(TAG, "Success persisting houses...") },
                { Log.e(TAG, "Failure persisting houses...") })

    private fun persistCharacters(characters: ArrayList<Character>) =
        insertCharacters(*ListUtils.toArray(Character::class.java, characters))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { Log.i(TAG, "Success persisting characters...") },
                { Log.e(TAG, "Failure persisting characters...") })

    private fun insertHouses(vararg houses: House): Completable =
        gotLocalDataSource.insertHouses(*houses)

    private fun insertCharacters(vararg characters: Character): Completable =
        gotLocalDataSource.insertCharacters(*characters)

    fun getCharacterByQuery(query: String): Observable<ArrayList<Character>> =
        gotLocalDataSource.getCharacterByQuery(query)
}