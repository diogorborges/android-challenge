package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotApiLocalDataSource
import es.npatarino.android.gotchallenge.data.model.Character
import io.reactivex.Single
import javax.inject.Inject

class CharactersHouseRepository @Inject constructor(
    private val gotLocalDataSource: GotApiLocalDataSource
) {

    companion object {
        private const val TAG = "CharHouseRepository"
    }

    fun getCharactersHouse(): Single<ArrayList<Character>> = Single.just(arrayListOf())
}