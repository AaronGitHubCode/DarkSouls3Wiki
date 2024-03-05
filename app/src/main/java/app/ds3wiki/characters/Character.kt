package app.ds3wiki.characters

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity
data class Character(
    @PrimaryKey(autoGenerate = true) val characterId: Int,
    @ColumnInfo("character_name") val name: String
)
