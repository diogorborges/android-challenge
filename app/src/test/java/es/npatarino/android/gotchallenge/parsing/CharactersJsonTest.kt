package es.npatarino.android.gotchallenge.parsing

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import es.npatarino.android.gotchallenge.common.JsonUtil
import es.npatarino.android.gotchallenge.data.model.Character
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CharactersJsonTest {

    lateinit var character: Character

    lateinit var characters: ArrayList<Character>

    @Before
    @Throws(IOException::class)
    fun setup() {
        val loadedJsonFromResource = JsonUtil.loadJsonFromResource("characters.json")

        val characterType = object : TypeToken<ArrayList<Character>>() {}.type
        characters = Gson().fromJson(loadedJsonFromResource, characterType)

        character = characters.first()
    }

    @Test
    fun testCharactersIsNotNull() {
        checkNotNull(characters)
    }

    @Test
    fun testCharacterIsNotNull() {
        checkNotNull(characters.first())
    }

    @Test
    fun testCharacter() {
        assertEquals("Tyrion Lannister", character.name)

        assertEquals(
            "In A Game of Thrones (1996), Tyrion is introduced as the third and youngest child of wealthy and powerful Tywin Lannister, the former Hand of the King. Tyrion's elder sister Cersei is the Queen of Westeros by virtue of her marriage to King Robert Baratheon, and Cersei's male twin Jaime is one of the Kingsguard, the royal security detail. Described as an ugly ('for all the world like a gargoyle'), malformed dwarf with mismatched green and black eyes, Tyrion possesses the pale blond hair of a Lannister but has a complicated relationship with the rest of them.",
            character.description
        )

        assertEquals(
            "https://firebasestorage.googleapis.com/v0/b/project-8424324399725905479.appspot.com/o/got/8462e9de-a1f6-4333-a83b-2cf1f1521827.jpg",
            character.imageUrl
        )

        assertEquals(
            "House Lannister",
            character.houseName
        )

        assertEquals(
            "https://firebasestorage.googleapis.com/v0/b/project-8424324399725905479.appspot.com/o/got/lannister.jpg",
            character.houseImageUrl
        )

        assertEquals("50fab25b", character.houseId)
    }
}