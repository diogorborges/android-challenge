package es.npatarino.android.gotchallenge.data.repository

import es.npatarino.android.gotchallenge.data.local.GotLocalDataSource
import es.npatarino.android.gotchallenge.data.model.House
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HousesRepositoryTest {

    @InjectMocks
    private lateinit var houseRepository: HousesRepository

    @Mock
    private lateinit var gotLocalDataSource: GotLocalDataSource

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getHouses() {
        val house = Mockito.mock(House::class.java)
        val houseList = arrayListOf<House>()
        houseList.add(house)

        `when`(houseRepository.getHouses()).thenReturn(Single.just(houseList))

        houseRepository.getHouses()

        verify(gotLocalDataSource).getHouses()
    }
}