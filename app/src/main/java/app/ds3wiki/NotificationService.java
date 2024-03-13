package app.ds3wiki;

public interface NotificationService {
    void onNotify(final String title, final String content, final int smallIcon);
    void onNotify(final String title, final String content, final int smallIcon, final int largeIcon);
}
