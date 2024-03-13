package app.ds3wiki;

import android.app.Notification;
import android.app.NotificationChannel;

import android.app.NotificationManager;
import android.content.Intent;

import android.os.IBinder;

import androidx.annotation.Nullable;

import java.util.Calendar;

public final class SpecialDayService extends ForegroundService {
    private static final int DAY_OF_MONTH = 24;
    private static final int MONTH = 3;

    private static final int SERVICE_NOTIFICATION_UNIQUE_ID = 0x1000;

    private static final String SERVICE_NOTIFICATION_NAME = "SPECIAL_DAY_NOTIFICATION_ID";
    private static final String SERVICE_NOTIFICATION_ID = "SPECIAL_DAY_NOTIFICATION_NAME";

    private Notification foregroundNotification;

    @Override
    public void onCreate() {
        super.onCreate();

        final var notificationChannel = new NotificationChannel(SERVICE_NOTIFICATION_ID, SERVICE_NOTIFICATION_NAME, NotificationManager.IMPORTANCE_DEFAULT);
        getNotificationManager().createNotificationChannel(notificationChannel);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        launchNotification("", "", 0);
        return START_NOT_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    protected void launchNotification(String title, String content, int smallIcon) {
        final var notificationBuilder = new Notification.Builder(this, SERVICE_NOTIFICATION_ID);

        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(content);
        notificationBuilder.setSmallIcon(smallIcon);

        final var notification = notificationBuilder.build();

        startForeground(SERVICE_NOTIFICATION_UNIQUE_ID, notification);

        final var date = Calendar.getInstance();

        final var day = date.get(Calendar.DAY_OF_MONTH);
        final var month = date.get(Calendar.MONTH);

        if (day == DAY_OF_MONTH && month == MONTH) {
            getNotificationManager().notify(SERVICE_NOTIFICATION_UNIQUE_ID, notification);
        }
    }

    @Override
    protected void launchNotification(String title, String content, int smallIcon, int largeIcon) {
        /*
        final var notificationBuilder = new Notification.Builder(this, SERVICE_NOTIFICATION_ID);

        notificationBuilder.setContentTitle(title);
        notificationBuilder.setContentText(content);
        notificationBuilder.setSmallIcon(smallIcon);
        notificationBuilder.setLargeIcon(android.graphics.BitmapFactory.decodeResource(getResources(), largeIcon));

        final var notification = notificationBuilder.build();

        getNotificationManager().notify(SERVICE_NOTIFICATION_UNIQUE_ID, notification);
         */

        return;
    }
}
