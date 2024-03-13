package app.ds3wiki.home;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;
import java.util.ArrayList;

public final class HomeViewModel extends ViewModel {
    private HomeViewModel() {}

    private final LiveData<String> characterName = new MutableLiveData<>();

    public LiveData<String> getCharacterName() {
        return characterName;
    }
}
