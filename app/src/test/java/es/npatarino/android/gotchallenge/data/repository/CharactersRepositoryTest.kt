package es.npatarino.android.gotchallenge.data.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import es.npatarino.android.gotchallenge.data.local.GotLocalDataSource
import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.data.remote.GotRemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class CharactersRepositoryTest {

    @InjectMocks
    private lateinit var charactersRepository: CharactersRepository

    @Mock
    private lateinit var gotLocalDataSource: GotLocalDataSource

    @Mock
    private lateinit var gotRemoteDataSource: GotRemoteDataSource

    @Mock
    private lateinit var context: Context

    private lateinit var networkInfo: NetworkInfo

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val connectivityManager = Mockito.mock(ConnectivityManager::class.java)
        networkInfo = Mockito.mock(NetworkInfo::class.java)

        `when`(context.getSystemService("connectivity")).thenReturn(connectivityManager)
        `when`(connectivityManager.activeNetworkInfo).thenReturn(networkInfo)
    }

    @Test
    fun getCharactersAPI() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        val emptyCharacterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(networkInfo.isConnected).thenReturn(true)
        `when`(gotLocalDataSource.getCharacters()).thenReturn(Single.just(emptyCharacterList))
        `when`(gotRemoteDataSource.getCharacters()).thenReturn(Single.just(characterList))
        `when`(charactersRepository.getCharacters()).thenReturn(
            Single.just(
                emptyCharacterList
            )
        )

        val testObserver = TestObserver<ArrayList<Character>>()
        charactersRepository.getCharacters().subscribe(testObserver)

        verify(gotLocalDataSource).insertCharacters(character)
    }

    @Test
    fun getCharactersFromDB() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(gotLocalDataSource.getCharacters()).thenReturn(Single.just(characterList))
        `when`(charactersRepository.getCharacters()).thenReturn(
            Single.just(
                characterList
            )
        )

        val testObserver = TestObserver<ArrayList<Character>>()
        charactersRepository.getCharacters().subscribe(testObserver)

        testObserver.assertSubscribed()
    }

    @Test
    fun getCharactersFromAPINetworkException() {
        val emptyCharacterList = arrayListOf<Character>()

        `when`(networkInfo.isConnected).thenReturn(false)
        `when`(gotLocalDataSource.getCharacters()).thenReturn(Single.just(emptyCharacterList))

        val testObserver = TestObserver<ArrayList<Character>>()
        charactersRepository.getCharacters().subscribe(testObserver)

        testObserver.errors()
    }

    @Test
    fun getCharacterByQuery() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)
        val query = "Tyrion"

        `when`(charactersRepository.getCharacterByQuery(query)).thenReturn(
            Observable.just(
                characterList
            )
        )

        charactersRepository.getCharacterByQuery(query)

        verify(gotLocalDataSource).getCharacterByQuery(query)
    }
}