package app.ds3wiki;

public interface AudioService {
    void onSelectAudioResource(final int resource, final boolean background);
    void onPlay(final boolean background);
    void onPause(final boolean background);
}
