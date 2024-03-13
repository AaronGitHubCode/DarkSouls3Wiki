package app.ds3wiki.activities;

import app.ds3wiki.R;
import app.ds3wiki.IOService;
import app.ds3wiki.AudioService;
import app.ds3wiki.SpecialDayService;

import app.ds3wiki.home.HomeFragment;

import app.ds3wiki.build.BuildFragment;

import app.ds3wiki.wiki.WikiFragment;

import static androidx.activity.result.contract.ActivityResultContracts.RequestMultiplePermissions;

import android.Manifest;

import android.os.Bundle;

import android.app.Notification;

import android.content.Intent;

import android.content.pm.PackageManager;

import android.media.MediaPlayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.HashMap;

public class MainActivity extends BaseActivity
implements IOService, AudioService {
    private static boolean notificationPermission;
    private static boolean readPermission = false;
    private static boolean writePermission = false;

    private String lastSection = "home";

    private MediaPlayer backgroundMusicPlayer = new MediaPlayer();
    private MediaPlayer soundEffectsPlayer = new MediaPlayer();

    public MainActivity() {
        super(R.layout.main_activity_layout);
    }

    private static final String[] REQUIRED_PERMISSIONS = new String[] {
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.INTERNET,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    /**TODO: Comprobar correcto funcionamiento de almacenamiento de logs del programa*/
    private void writeInitLog() {
        final var builder = new StringBuilder();

        builder.append(java.util.Calendar.getInstance().getTime());
        builder.append("Welcome back!");

        try {
            final var tempFile = Files.createTempFile(null, ".tmp");
            onWrite(tempFile, builder.toString().getBytes());
            final var data = onRead(tempFile);

            assert data.isPresent();

            for (final var b : data.get()) {
                android.util.Log.i("log", String.valueOf((char) b));
            }
        }catch(final IOException ignored) {}
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final var requestMultiplePermissions = registerForActivityResult(new RequestMultiplePermissions(), permissions -> {
            permissions.keySet().forEach(key -> android.util.Log.i("boolean", key));

            final var notificationPermission = permissions.get(Manifest.permission.POST_NOTIFICATIONS);
            final var readPermission = permissions.get(Manifest.permission.READ_EXTERNAL_STORAGE);
            final var writePermission = permissions.get(Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (notificationPermission)
                MainActivity.notificationPermission = true;

            if (readPermission)
                MainActivity.readPermission = true;

            if (writePermission)
                MainActivity.writePermission = true;

            if (writePermission)
                writeInitLog();
        });

        requestMultiplePermissions.launch(REQUIRED_PERMISSIONS);

        final var fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, new HomeFragment(), "home_fragment");
        fragmentTransaction.commit();
    }

    @Override
    protected void onStart() {
        super.onStart();

        final var specialDayServiceIntent = new Intent(this, SpecialDayService.class);

        startForegroundService(specialDayServiceIntent);

        final var bottomNavigationView = (BottomNavigationView) findViewById(R.id.top_navigation_view);

        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            final var itemId = item.getItemId();

            if (itemId == R.id.build) {
                if (! lastSection.equals("build")) {
                    final var fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.static_slide);
                    fragmentTransaction.replace(R.id.fragment_container, new BuildFragment(), "build_fragment");
                    fragmentTransaction.commit();
                    lastSection = "build";
                }
            }else if (itemId == R.id.home) {
                if (! lastSection.equals("home")) {
                    final var fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.static_slide);
                    fragmentTransaction.replace(R.id.fragment_container, new HomeFragment(), "home_fragment");
                    fragmentTransaction.commit();
                    lastSection = "home";
                }
            }else if (itemId == R.id.wiki) {
                if (! lastSection.equals("wiki")) {
                    final var fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in, R.anim.static_slide);
                    fragmentTransaction.replace(R.id.fragment_container, new WikiFragment(), "wiki_fragment");
                    fragmentTransaction.commit();
                    lastSection = "wiki";
                }
            }

            return true;
        });
    }

    @Deprecated
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 0x0100) {
            final var permissionsWithResult = new HashMap<String, Integer>();

            int i;
            for (i = 0; i < permissions.length; i++) {
                permissionsWithResult.put(permissions[i], grantResults[i]);
            }

            if (permissionsWithResult.get(Manifest.permission.INTERNET) == PackageManager.PERMISSION_DENIED)
                finish();

            permissionsWithResult.entrySet().stream().filter(value -> ! value.getKey().equals(Manifest.permission.INTERNET)).forEach(permission -> {
                final var key = permission.getKey();

                switch (key) {
                    case Manifest.permission.POST_NOTIFICATIONS -> notificationPermission = permission.getValue() == PackageManager.PERMISSION_GRANTED ? true : false;
                    case Manifest.permission.READ_EXTERNAL_STORAGE -> readPermission = permission.getValue() == PackageManager.PERMISSION_GRANTED ? true : false;
                }
            });
        }
    }

    @Override
    public void onNotify(String title, String content, int smallIcon) {
        if (notificationPermission) {
            mNotificationManager.notify(NOTIFICATION_UNIQUE_ID, new Notification.Builder(this, NOTIFICATION_ID)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(smallIcon)
                    .build()
            );
        }
    }

    @Override
    public void onNotify(String title, String content, int smallIcon, int largeIcon) {
        if (notificationPermission) {
            mNotificationManager.notify(NOTIFICATION_UNIQUE_ID, new Notification.Builder(this, NOTIFICATION_ID)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setSmallIcon(smallIcon)
                    .setLargeIcon(android.graphics.BitmapFactory.decodeResource(getResources(), largeIcon))
                    .build()
            );
        }
    }

    @Override
    public void onWrite(File file, byte[] data)
    throws IOException {
        if (writePermission) {
            final var assets = getResources().getAssets();

            try (final var outputStream = assets.openFd(file.getPath()).createOutputStream()) {
                outputStream.write(data);
            }
        }
    }

    @Override
    public void onWrite(Path path, byte[] data)
    throws IOException {
        if (writePermission) {
            final var assets = getResources().getAssets();

            try (final var outputStream = assets.openFd(path.toFile().getPath()).createOutputStream()) {
                outputStream.write(data);
            }
        }
    }

    @Override
    public java.util.Optional<byte[]> onRead(Path path)
    throws IOException {
        if (readPermission) {
            final var assets = getResources().getAssets();

            try (final var inputStream = assets.openFd(path.toFile().getPath()).createInputStream()) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    return java.util.Optional.of(inputStream.readAllBytes());
                }
            }
        }

        return java.util.Optional.empty();
    }

    @Override
    public java.util.Optional<byte[]> onRead(File file)
    throws IOException {
        if (readPermission) {
            final var assets = getResources().getAssets();

            try (final var inputStream = assets.openFd(file.getPath()).createInputStream()) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    return java.util.Optional.of(inputStream.readAllBytes());
                }
            }
        }

        return java.util.Optional.empty();
    }

    @Override
    public void onSelectAudioResource(int resource, boolean background) {
        if (background) {
            backgroundMusicPlayer = MediaPlayer.create(this, resource);
        }else {
            soundEffectsPlayer = MediaPlayer.create(this, resource);
        }
    }

    @Override
    public void onPlay(boolean background) {
        if (background) {
            if (! backgroundMusicPlayer.isPlaying())
                backgroundMusicPlayer.start();
        }else {
            if (! soundEffectsPlayer.isPlaying())
                soundEffectsPlayer.start();
        }
    }

    @Override
    public void onPause(boolean background) {
        if (background) {
            if (backgroundMusicPlayer.isPlaying())
                backgroundMusicPlayer.pause();
        }else {
            if (soundEffectsPlayer.isPlaying())
                soundEffectsPlayer.pause();
        }
    }
}
