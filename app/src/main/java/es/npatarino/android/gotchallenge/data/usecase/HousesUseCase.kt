package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.data.repository.HousesRepository
import io.reactivex.Single
import javax.inject.Inject

class HousesUseCase @Inject constructor(private val housesRepository: HousesRepository) {
    fun getHouses(): Single<ArrayList<House>> = housesRepository.getHouses()
}