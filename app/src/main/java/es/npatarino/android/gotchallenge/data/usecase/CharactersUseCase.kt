package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersRepository
import io.reactivex.Single
import javax.inject.Inject

class CharactersUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    fun getCharacters(): Single<ArrayList<Character>> = charactersRepository.getCharacters()
}