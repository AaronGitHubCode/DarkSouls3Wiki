package app.ds3wiki.home;

import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;

import app.ds3wiki.NotificationService;
import app.ds3wiki.R;

public final class HomeFragment extends Fragment {
    private NotificationService notificationService;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment_layout, container, false);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        notificationService = (NotificationService) context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final var context = view.getContext();

        final var lifecycleOwner = getViewLifecycleOwner();
        final var homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        homeViewModel.getCharacterName().observe(lifecycleOwner, data -> {

        });
    }
}
