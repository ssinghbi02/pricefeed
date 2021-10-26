package org.example.repository;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public abstract class AbstractFileRepository {

    public AbstractFileRepository() {
    }

    public AbstractFileRepository(String location) {
        this();
        read(location);
    }

    protected void read(String location) {
        InputStream inputStream = this.getClass().getResourceAsStream("/prices.csv");
        if (inputStream == null) {
            throw new IllegalArgumentException("Could not load file:" + location);
        }
        try {
            load(inputStream);
        } catch (final Exception exception) {
            throw new IllegalArgumentException(location + ": " + exception.getMessage(), exception);
        } finally {
            closeQuietly(inputStream);
        }
    }

    protected abstract void load(InputStream inputStream);

    public static void closeQuietly(Closeable closable) {
        if (closable != null) {
            try {
                closable.close();
            } catch (IOException exception) {

            }
        }
    }
}
