package app.ds3wiki.listeners;

import androidx.annotation.NonNull;

@FunctionalInterface
public interface INotification {
    void onNotify(final @NonNull String title, final @NonNull String content, final int smallIcon, final int largeIcon);
}
