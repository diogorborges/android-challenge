package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotLocalDataSource
import es.npatarino.android.gotchallenge.data.model.Character
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class CharactersHouseRepositoryTest {

    @InjectMocks
    private lateinit var charactersHouseRepository: CharactersHouseRepository

    @Mock
    private lateinit var gotLocalDataSource: GotLocalDataSource

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharactersHouse() {
        val character = Mockito.mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val houseId = "1"

        Mockito.`when`(charactersHouseRepository.getCharactersHouse(houseId)).thenReturn(Single.just(characterList))

        charactersHouseRepository.getCharactersHouse(houseId)

        Mockito.verify(gotLocalDataSource).getCharactersHouse(houseId)
    }
}