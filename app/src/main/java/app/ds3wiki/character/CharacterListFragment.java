package app.ds3wiki.character;

import app.ds3wiki.R;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.function.Supplier;

public final class CharacterListFragment extends Fragment {
    private static final int TOTAL_CHARACTER_PER_ROW = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.character_list_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final var context = view.getContext();
        final var recyclerView = (RecyclerView) view.findViewById(R.id.character_list);

        recyclerView.setLayoutManager(new GridLayoutManager(context, TOTAL_CHARACTER_PER_ROW));

        recyclerView.setAdapter(((Supplier<CharacterAdapter>)() -> {
            final var adapter = new CharacterAdapter();
            adapter.setOnClickListener(character -> {
                final var fragmentTransaction = getParentFragmentManager().beginTransaction();

                fragmentTransaction.replace(R.id.fragment_container, new CharacterFragment(character), "character_fragment");
                fragmentTransaction.commit();
            });
            return adapter;
        }).get());
    }
}
