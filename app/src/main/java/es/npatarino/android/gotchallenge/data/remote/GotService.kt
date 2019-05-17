package es.npatarino.android.gotchallenge.data.remote

import es.npatarino.android.gotchallenge.data.model.Character
import io.reactivex.Single
import retrofit2.http.GET

interface GotService {

    companion object {
        const val GET_CHARACTERS = "characters.json"
    }

    @GET(GET_CHARACTERS)
    fun getCharacters(): Single<ArrayList<Character>>
}
