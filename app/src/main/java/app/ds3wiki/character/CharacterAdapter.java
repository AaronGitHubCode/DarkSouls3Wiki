package app.ds3wiki.character;

import app.ds3wiki.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static androidx.recyclerview.widget.RecyclerView.Adapter;
import static androidx.recyclerview.widget.RecyclerView.ViewHolder;

import androidx.annotation.NonNull;

final class CharacterAdapter extends Adapter<CharacterViewHolder> {
    private final CharacterRepository characterRepository = CharacterRepository.getInstance();

    @FunctionalInterface
    public interface OnClickListener {
        void onClick(final Character character);
    }

    private OnClickListener onClickListener;

    public void setOnClickListener(final OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CharacterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.character_row_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        final var root = holder.getRoot();
        final var context = root.getContext();

        root.setOnClickListener(v -> {
            onClickListener.onClick(characterRepository.getCharacter(position));
        });
    }

    @Override
    public int getItemCount() {
        return characterRepository.getRepositorySize();
    }
}

final class CharacterViewHolder extends ViewHolder {
    private final View root;

    public CharacterViewHolder(final View root) {
        super(root);
        this.root = root;
    }

    View getRoot() {
        return root;
    }
}