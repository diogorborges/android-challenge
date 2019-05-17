package es.npatarino.android.gotchallenge.presentation.houses

import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.data.usecase.HousesUseCase
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

class HousesPresenterTest : DefaultPluginTestSetup() {

    @InjectMocks
    private lateinit var housesPresenter: HousesPresenter

    @Mock
    private lateinit var view: HousesFragment

    @Mock
    private lateinit var housesUseCase: HousesUseCase

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getHouses() {
        val house = mock(House::class.java)
        val houseList = arrayListOf<House>()
        houseList.add(house)

        `when`(housesUseCase.getHouses()).thenReturn(Single.just(houseList))

        housesPresenter.setView(view)

        verify(view).showLoader(true)
        verify(view).showHouses(ArgumentMatchers.anyList())
        verify(view).showLoader(false)
    }
}