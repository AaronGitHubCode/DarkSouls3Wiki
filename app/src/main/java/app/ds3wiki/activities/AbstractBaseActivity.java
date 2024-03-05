package app.ds3wiki.activities;

import android.os.Bundle;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

public abstract class AbstractBaseActivity extends AppCompatActivity {
    static final int NOTIFICATION_REQUEST_CODE = 0x0100;
    static final int NOTIFICATION_UNIQUE_ID = 0x1000;

    static final String NOTIFICATION_ID = "DARK_WIKI_NOTIFICATION_ID";
    static final String NOTIFICATION_NAME = "DARK_WIKI_NOTIFICATION_NAME";

    private NotificationManager notificationManager;

    public AbstractBaseActivity() {
        super();
    }

    public AbstractBaseActivity(final int layout) {
        super(layout);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(new NotificationChannel(NOTIFICATION_ID, NOTIFICATION_NAME, NotificationManager.IMPORTANCE_DEFAULT));
    }

    protected final NotificationManager getNotificationManager() {
        return notificationManager;
    }
}
