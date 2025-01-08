package ru.job4j.pool;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.*;

class RolColSumTest {

    @Test
    void whenSum() {
        RolColSum rolColSum = new RolColSum();
        int[][] matrix = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Sums[] result = {new Sums(6, 3), new Sums(6, 6), new Sums(6, 9)};
        assertThat(rolColSum.sum(matrix)).isEqualTo(result);
    }

    @Test
    void whenAsyncSum() throws ExecutionException, InterruptedException {
        RolColSum rolColSum = new RolColSum();
        int[][] matrix = {{1, 2, 3}, {1, 2, 3}, {1, 2, 3}};
        Sums[] result = {new Sums(6, 3), new Sums(6, 6), new Sums(6, 9)};
        assertThat(rolColSum.asyncSum(matrix)).isEqualTo(result);
    }
}