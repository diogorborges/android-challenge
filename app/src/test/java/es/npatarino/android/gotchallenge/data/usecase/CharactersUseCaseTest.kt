package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.repository.CharactersRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CharactersUseCaseTest {

    @InjectMocks
    private lateinit var charactersUseCase: CharactersUseCase

    @Mock
    private lateinit var charactersRepository: CharactersRepository

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharacters() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(charactersRepository.getCharacters()).thenReturn(Single.just(characterList))

        charactersUseCase.getCharacters()

        verify(charactersRepository).getCharacters()
    }
}