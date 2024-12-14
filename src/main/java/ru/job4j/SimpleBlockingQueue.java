package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final int size;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (queue) {
            while (queue.size() >= size) {
                queue.wait();
            }
            queue.offer(value);
            queue.notify();
        }
    }

    public T poll() throws InterruptedException {
        synchronized (queue) {
            T result;
            while (queue.isEmpty()) {
                queue.wait();
            }
            result = queue.poll();
            queue.notify();
            return result;
        }
    }
}

