package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersRepository
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CharactersByQueryUseCaseTest {

    @InjectMocks
    private lateinit var charactersByQueryUseCase: CharactersByQueryUseCase

    @Mock
    private lateinit var characterRepository: CharactersRepository

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharacterByQuery() {
        val character = Mockito.mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val query = "Tyrion"

        `when`(characterRepository.getCharacterByQuery(query)).thenReturn(Observable.just(characterList))

        charactersByQueryUseCase.getCharacterByQuery(query)

        verify(characterRepository).getCharacterByQuery(query)
    }
}