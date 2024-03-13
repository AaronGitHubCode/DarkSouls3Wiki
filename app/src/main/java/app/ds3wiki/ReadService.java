package app.ds3wiki;

import java.io.File;
import java.io.IOException;

import java.util.Optional;

import java.nio.file.Path;

public interface ReadService {
    Optional<byte[]> onRead(final Path path) throws IOException;
    Optional<byte[]> onRead(final File file) throws IOException;
}
