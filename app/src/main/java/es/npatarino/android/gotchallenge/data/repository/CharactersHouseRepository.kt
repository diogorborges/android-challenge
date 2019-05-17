package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotLocalDataSource
import es.npatarino.android.gotchallenge.data.model.Character
import io.reactivex.Single
import javax.inject.Inject

class CharactersHouseRepository @Inject constructor(
    private val gotLocalDataSource: GotLocalDataSource
) {
    fun getCharactersHouse(houseId: String): Single<ArrayList<Character>> =
        gotLocalDataSource.getCharactersHouse(houseId)
}