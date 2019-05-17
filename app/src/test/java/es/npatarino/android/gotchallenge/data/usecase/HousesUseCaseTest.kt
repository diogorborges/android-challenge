package es.npatarino.android.gotchallenge.data.usecase

import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.data.repository.HousesRepository
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HousesUseCaseTest {

    @InjectMocks
    private lateinit var housesUseCase: HousesUseCase

    @Mock
    private lateinit var housesRepository: HousesRepository

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getHouses() {
        val house = mock(House::class.java)
        val houseList = arrayListOf<House>()
        houseList.add(house)

        `when`(housesRepository.getHouses()).thenReturn(Single.just(houseList))

        housesUseCase.getHouses()

        verify(housesRepository).getHouses()
    }
}