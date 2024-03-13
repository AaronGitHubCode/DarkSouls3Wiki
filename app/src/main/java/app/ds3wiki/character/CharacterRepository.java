package app.ds3wiki.character;

import app.ds3wiki.annotations.Singleton;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Singleton
public final class CharacterRepository {
    private static CharacterRepository repository;

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

    public Character getCharacter(final int position) {
        return characters.get(position);
    }

    public void addCharacters(final Character... characters) {
        this.characters.addAll(Arrays.asList(characters));
    }

    public void removeCharacters(final Character... characters) {
        this.characters.removeAll(Arrays.asList(characters));
    }

    public static CharacterRepository getInstance() {
        return repository;
    }
}
