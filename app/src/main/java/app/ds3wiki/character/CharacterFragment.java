package app.ds3wiki.character;

import app.ds3wiki.R;
import app.ds3wiki.AudioService;

import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

public final class CharacterFragment extends Fragment {
    private AudioService audioService;

    private final Character character;

    public CharacterFragment(final Character character) {
        this.character = character;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_fragment_layout, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        audioService = (AudioService) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        audioService.onSelectAudioResource(0, true);
        audioService.onSelectAudioResource(0, false);

        final var context = view.getContext();
    }
}
