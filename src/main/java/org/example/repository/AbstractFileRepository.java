package org.example.repository;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractFileRepository {

    private String location;
    InputStream inputStream;
    public AbstractFileRepository(String location) {
        this.location = location;
    }


    protected void read() {
        inputStream = this.getClass().getResourceAsStream(location);
        if (inputStream == null) {
            throw new IllegalArgumentException("Could not load file:" + location);
        }
        try {
            load(inputStream);
        } catch (final Exception exception) {
            throw new IllegalArgumentException(location + ": " + exception.getMessage(), exception);
        } finally {
            closeQuietly();
        }
    }

    protected abstract void load(InputStream inputStream);

    public void closeQuietly() {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException exception) {

            }
        }
    }
}
