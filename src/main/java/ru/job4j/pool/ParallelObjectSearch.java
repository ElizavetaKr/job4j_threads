package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelObjectSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T object;

    private final int from;
    private final int to;

    public ParallelObjectSearch(T[] array, T object, int from, int to) {
        this.array = array;
        this.object = object;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch(array, object, from, to);
        }
        int middle = (from + to) / 2;
        ParallelObjectSearch leftSearch = new ParallelObjectSearch(array, object, from, middle);
        ParallelObjectSearch rightSearch = new ParallelObjectSearch(array, object, middle + 1, to);
        leftSearch.fork();
        rightSearch.fork();
        int left = (int) leftSearch.join();
        int right = (int) rightSearch.join();
        return (left != -1) ? left : right;
    }

    private int linearSearch(T[] array, T object, int from, int to) {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(object)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public int search(T[] array, T object) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return (int) forkJoinPool.invoke(new ParallelObjectSearch(array, object, 0, array.length - 1));
    }
}
