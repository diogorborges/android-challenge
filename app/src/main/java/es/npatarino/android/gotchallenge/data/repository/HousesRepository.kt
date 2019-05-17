package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotLocalDataSource
import es.npatarino.android.gotchallenge.data.model.House
import io.reactivex.Single
import javax.inject.Inject

class HousesRepository @Inject constructor(
    private val gotLocalDataSource: GotLocalDataSource
) {
    fun getHouses(): Single<ArrayList<House>> = gotLocalDataSource.getHouses()
}