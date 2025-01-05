package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final int size = Runtime.getRuntime().availableProcessors();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        try {
                            tasks.poll();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            threads.add(thread);
            thread.start();
        }
    }

    public void work(Runnable job) throws InterruptedException {
        tasks.offer(job);
    }

    public void shutdown() {
        for (Thread th : threads) {
            th.interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        threadPool.work(() -> System.out.println("..."));
        threadPool.tasks.poll().run();
    }
}