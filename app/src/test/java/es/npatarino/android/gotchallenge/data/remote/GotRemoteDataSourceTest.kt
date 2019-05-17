package es.npatarino.android.gotchallenge.data.remote

import es.npatarino.android.gotchallenge.data.model.Character
import es.npatarino.android.gotchallenge.test.DefaultPluginTestSetup
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

class GotRemoteDataSourceTest : DefaultPluginTestSetup() {

    @InjectMocks
    private lateinit var remoteDataSource: GotRemoteDataSource

    @Mock
    private lateinit var gotService: GotService

    @Before
    fun setup() = MockitoAnnotations.initMocks(this)

    @Test
    fun testGetSources() {
        val character = mock(Character::class.java)
        val characterList = arrayListOf<Character>()
        characterList.add(character)

        `when`(gotService.getCharacters()).thenReturn(Single.just(characterList))
        val testObserver = TestObserver<ArrayList<Character>>()

        remoteDataSource.getCharacters().subscribe(testObserver)

        verify(gotService).getCharacters()
    }
}