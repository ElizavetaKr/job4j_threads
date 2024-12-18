package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public CASCount(int count) {
        this.count.addAndGet(count);
    }

    public CASCount() {
    }

    public void increment() throws UnsupportedOperationException {
        count.getAndIncrement();
    }

    public int get() throws UnsupportedOperationException {
        return count.get();
    }
}