package app.ds3wiki;

import android.app.NotificationManager;
import android.app.Service;

import androidx.annotation.CallSuper;

public abstract class ForegroundService extends Service {
    private NotificationManager sNotificationManager;

    protected final NotificationManager getNotificationManager() {
        return sNotificationManager;
    }

    @Override
    @CallSuper
    public void onCreate() {
        sNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    protected abstract void launchNotification(final String title, final String content, final int smallIcon);
    protected abstract void launchNotification(final String title, final String content, final int smallIcon, final int largeIcon);
}
