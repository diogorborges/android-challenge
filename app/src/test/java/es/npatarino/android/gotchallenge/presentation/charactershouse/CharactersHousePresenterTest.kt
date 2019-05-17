package es.npatarino.android.gotchallenge.presentation.charactershouse

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.usecase.CharactersHouseUseCase
import es.npatarino.android.gotchallenge.test.DefaultPluginTestSetup
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

class CharactersHousePresenterTest : DefaultPluginTestSetup() {

    @InjectMocks
    private lateinit var charactersHousePresenter: CharactersHousePresenter

    @Mock
    private lateinit var view: CharactersHouseFragment

    @Mock
    private lateinit var charactersHouseUseCase: CharactersHouseUseCase

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharactersHouse() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val houseId = "1"

        `when`(charactersHouseUseCase.getCharactersHouse(houseId)).thenReturn(
            Single.just(
                characterList
            )
        )

        charactersHousePresenter.setView(view, houseId)

        verify(view).showLoader(true)
        verify(view).showCharacters(ArgumentMatchers.anyList())
        verify(view).showLoader(false)
    }
}