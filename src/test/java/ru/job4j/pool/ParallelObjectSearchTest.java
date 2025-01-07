package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ParallelObjectSearchTest {

    @Test
    void whenTypeString() {
        String[] array = {"A", "V", "D", "B", "C"};
        String obj = "V";
        ParallelObjectSearch ps = new ParallelObjectSearch(array, obj, 0, array.length - 1);
        assertThat(ps.search(array, obj)).isEqualTo(1);
    }

    @Test
    void whenTypeInteger() {
        Integer[] array = {1, 2, 4, 5};
        Integer obj = 5;
        ParallelObjectSearch ps = new ParallelObjectSearch(array, obj, 0, array.length - 1);
        assertThat(ps.search(array, obj)).isEqualTo(3);
    }

    @Test
    void whenRecursiveSearch() {
        Integer[] array = {1, 2, 4, 5, 11, 21, 41, 51, 22, 43, 51, 42, 54, 65};
        Integer obj = 51;
        ParallelObjectSearch ps = new ParallelObjectSearch(array, obj, 0, array.length - 1);
        assertThat(ps.search(array, obj)).isEqualTo(7);
    }

    @Test
    void whenObjectNotFoundThenMinus1() {
        Integer[] array = {1, 2, 4, 5, 11, 21, 41, 51, 22, 43, 51, 42, 54, 65};
        Integer obj = 100;
        ParallelObjectSearch ps = new ParallelObjectSearch(array, obj, 0, array.length - 1);
        assertThat(ps.search(array, obj)).isEqualTo(-1);
    }
}