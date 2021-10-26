package org.example.event;

public interface Event<T> {
    T getPayload();
    EventType getType();
}
