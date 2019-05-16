package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotApiLocalDataSource
import es.npatarino.android.gotchallenge.data.model.House
import io.reactivex.Single
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val gotLocalDataSource: GotApiLocalDataSource
) {
    fun getHouses(): Single<ArrayList<House>> = gotLocalDataSource.getHouses()
}