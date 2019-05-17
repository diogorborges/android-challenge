package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersHouseRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CharactersHouseUseCaseTest {

    @InjectMocks
    private lateinit var charactersHouseUseCase: CharactersHouseUseCase

    @Mock
    private lateinit var charactersHouseRepository: CharactersHouseRepository

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharactersHouse() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val houseId = "1"

        `when`(charactersHouseRepository.getCharactersHouse(houseId)).thenReturn(Single.just(characterList))

        charactersHouseUseCase.getCharactersHouse(houseId)

        verify(charactersHouseRepository).getCharactersHouse(houseId)
    }
}