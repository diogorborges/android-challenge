package es.npatarino.android.gotchallenge.presentation.characters

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.usecase.CharactersByQueryUseCase
import es.npatarino.android.gotchallenge.data.usecase.CharactersUseCase
import es.npatarino.android.gotchallenge.test.DefaultPluginTestSetup
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CharactersPresenterTest : DefaultPluginTestSetup() {

    @InjectMocks
    private lateinit var charactersPresenter: CharactersPresenter

    @Mock
    private lateinit var view: CharactersFragment

    @Mock
    private lateinit var charactersUseCase: CharactersUseCase

    @Mock
    private lateinit var charactersByQueryUseCase: CharactersByQueryUseCase

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharacter() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(charactersUseCase.getCharacters()).thenReturn(
            Single.just(
                characterList
            )
        )

        charactersPresenter.setView(view)

        verify(view).showLoader(true)
        verify(view).showCharacters(ArgumentMatchers.anyList())
        verify(view).showLoader(false)
    }

    @Test
    fun getCharacterByQuery() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val query = "Tyrion"

        `when`(charactersByQueryUseCase.getCharacterByQuery(query)).thenReturn(
            Observable.just(
                characterList
            )
        )

        charactersPresenter.getCharacterByQuery(query)

        verify(view).showCharacters(ArgumentMatchers.anyList())
    }
}