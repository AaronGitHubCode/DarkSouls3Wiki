package app.ds3wiki.character;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

@Entity
public final class Character {
    @PrimaryKey(autoGenerate = true) private int id;

    @ColumnInfo(name = "name") private String name;

    public Character(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
