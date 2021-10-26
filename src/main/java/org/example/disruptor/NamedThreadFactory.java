package org.example.disruptor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class NamedThreadFactory implements ThreadFactory {
    private AtomicInteger id = new AtomicInteger();
    private String name;

    public NamedThreadFactory(String name) {
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        int id = this.id.getAndIncrement();
        final String nameN = id == 0 ? this.name : this.name + "-" +id;
        return new Thread(r, nameN);
    }
}
