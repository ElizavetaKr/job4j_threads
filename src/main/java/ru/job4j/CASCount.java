package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicInteger;

@ThreadSafe
public class CASCount {
    private final AtomicInteger count = new AtomicInteger();

    public CASCount(int count) {
        this.count.addAndGet(count);
    }

    public CASCount() {
    }

    public void increment() {
        int newCount;
        do {
            newCount = count.get();
        } while (!count.compareAndSet(newCount, newCount + 1));
    }

    public int get() {
        return count.get();
    }
}