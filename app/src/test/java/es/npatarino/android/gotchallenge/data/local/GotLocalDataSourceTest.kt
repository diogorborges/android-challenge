package es.npatarino.android.gotchallenge.data.local

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.model.House
import es.npatarino.android.gotchallenge.test.DefaultPluginTestSetup
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GotLocalDataSourceTest : DefaultPluginTestSetup() {

    @InjectMocks
    private lateinit var localDataSource: GotLocalDataSource

    @Mock
    private lateinit var charactersDao: CharactersDao

    @Mock
    private lateinit var housesDao: HousesDao

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun getCharacters() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(charactersDao.getCharacters()).thenReturn(Single.just(characterList))
        val testObserver = TestObserver<ArrayList<Character>>()

        localDataSource.getCharacters().subscribe(testObserver)

        verify(charactersDao).getCharacters()
    }

    @Test
    fun insertCharacters() {
        val character = mock(Character::class.java)

        localDataSource.insertCharacters(character).subscribe()

        verify(charactersDao).insertCharacters(character)
    }

    @Test
    fun getHouses() {
        val house = mock(House::class.java)
        val houseList = arrayListOf<House>()
        houseList.add(house)

        `when`(housesDao.getHouses()).thenReturn(Single.just(houseList))
        val testObserver = TestObserver<ArrayList<House>>()

        localDataSource.getHouses().subscribe(testObserver)

        verify(housesDao).getHouses()
    }

    @Test
    fun insertHouses() {
        val house = mock(House::class.java)

        localDataSource.insertHouses(house).subscribe()

        verify(housesDao).insertHouses(house)
    }

    @Test
    fun getCharacterByQuery() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val query = "Tyrion"

        `when`(charactersDao.getCharacterByQuery(query)).thenReturn(Observable.just(characterList))
        val testObserver = TestObserver<ArrayList<Character>>()

        localDataSource.getCharacterByQuery(query).subscribe(testObserver)

        verify(charactersDao).getCharacterByQuery(query)
    }

    @Test
    fun getCharactersHouse() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val houseId = "1"

        `when`(charactersDao.getCharactersHouse(houseId)).thenReturn(Single.just(characterList))
        val testObserver = TestObserver<ArrayList<Character>>()

        localDataSource.getCharactersHouse(houseId).subscribe(testObserver)

        verify(charactersDao).getCharactersHouse(houseId)
    }
}