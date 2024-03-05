package app.ds3wiki.characters

import androidx.room.Database
import androidx.room.RoomDatabase

import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers

lateinit var characterDatabase: CharacterDatabase

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
}

suspend fun deleteDatabaseCharacter(vararg characters: Character): Job {
    return coroutineScope {
        launch {
            withContext(Dispatchers.IO) {
                val characterDao = characterDatabase.characterDao()
                characters.forEach { characterDao.removeCharacter(it) }
            }
        }
    }
}

suspend fun insertCharacterIntoDatabase(vararg characters: Character): Job {
    return coroutineScope {
        launch {
            withContext(Dispatchers.IO) {
                val characterDao = characterDatabase.characterDao()
                characters.forEach { characterDao.addNewCharacter(it) }
            }
        }
    }
}