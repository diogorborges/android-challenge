package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersRepository
import io.reactivex.Observable
import javax.inject.Inject

class CharactersByQueryUseCase @Inject constructor(private val charactersRepository: CharactersRepository) {
    fun getCharacterByQuery(query: String): Observable<ArrayList<Character>> =
        charactersRepository.getCharacterByQuery(query)
}