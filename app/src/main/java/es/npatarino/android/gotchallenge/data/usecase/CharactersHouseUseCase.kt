package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersHouseRepository
import io.reactivex.Single
import javax.inject.Inject

class CharactersHouseUseCase @Inject constructor(private val charactersHouseRepository: CharactersHouseRepository) {
    fun getCharactersHouse(houseId: String): Single<ArrayList<Character>> =
        charactersHouseRepository.getCharactersHouse(houseId)
}