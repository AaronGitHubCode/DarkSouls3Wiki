package app.ds3wiki;

import java.io.File;
import java.io.IOException;

import java.nio.file.Path;

public interface IOService extends ReadService {
    void onWrite(final File file, final byte[] data) throws IOException;
    void onWrite(final Path path, final byte[] data) throws IOException;
}
