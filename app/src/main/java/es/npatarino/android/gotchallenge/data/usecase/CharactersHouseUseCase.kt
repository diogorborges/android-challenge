package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersHouseRepository
import es.npatarino.android.gotchallenge.data.repository.CharactersRepository
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class CharactersHouseUseCase @Inject constructor(private val charactersHouseRepository: CharactersHouseRepository) {
    fun getCharactersHouse(): Single<ArrayList<Character>> = charactersHouseRepository.getCharactersHouse()
}