package app.ds3wiki.characters;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.ArrayList;

public final class CharacterViewModel extends ViewModel {
    private MutableLiveData<List<Character>> mutableCharactersLiveData = new MutableLiveData<>(new ArrayList<>());
    private final LiveData<List<Character>> charactersLiveData = mutableCharactersLiveData;

    public LiveData<List<Character>> getCharactersLiveData() {
        return charactersLiveData;
    }

    private void updateData() {
        final var mutableCharactersLiveData = this.mutableCharactersLiveData;
        this.mutableCharactersLiveData = mutableCharactersLiveData;
    }
}
