package app.ds3wiki.activities;

import android.os.Bundle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;

import android.media.MediaPlayer;

import androidx.annotation.Nullable;
import androidx.annotation.CallSuper;

import androidx.appcompat.app.AppCompatActivity;

import app.ds3wiki.NotificationService;

abstract class BaseActivity extends AppCompatActivity
implements NotificationService {
    static final String NOTIFICATION_ID = "DARK_SOULS_WIKI_NOTIFICATION_ID";
    static final String NOTIFICATION_NAME = "DARK_SOULS_WIKI_NOTIFICATION_NAME";

    static final int NOTIFICATION_UNIQUE_ID = 0xF;

    protected MediaPlayer mMediaPlayer;
    protected NotificationManager mNotificationManager;

    BaseActivity() {
        super();
    }

    BaseActivity(final int layout) {
        super(layout);
    }

    @Override
    @CallSuper
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final var notificationChannel = new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_DEFAULT);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotificationManager.createNotificationChannel(notificationChannel);
    }
}
