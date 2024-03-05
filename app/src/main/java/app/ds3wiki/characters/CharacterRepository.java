package app.ds3wiki.characters;

import java.util.List;
import java.util.ArrayList;

public final class CharacterRepository {
    private static final CharacterRepository repository;

    static {
        repository = new CharacterRepository();
    }

    private final List<Character> characters;

    private CharacterRepository() {
        characters = new ArrayList<>();
    }

    public int getRepositorySize() {
        return characters.size();
    }

    public Character getCharacter(final int index) {
        return characters.get(index);
    }

    public static CharacterRepository getInstance() {
        return repository;
    }
}
