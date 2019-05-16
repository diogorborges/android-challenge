package es.npatarino.android.gotchallenge.data.remote

import es.npatarino.android.gotchallenge.data.model.character.Character
import io.reactivex.Single
import javax.inject.Inject

class GotRemoteDataSource @Inject constructor(private val gotService: GotService) {
    fun getCharacters(): Single<ArrayList<Character>> = gotService.getCharacters()
}