package app.ds3wiki.characters

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete

@Dao
interface CharacterDao {
    @Query("SELECT * FROM Character")
    fun getAllCharacters(): List<Character>

    @Insert
    fun addNewCharacter(character: Character)

    @Delete
    fun removeCharacter(character: Character)
}