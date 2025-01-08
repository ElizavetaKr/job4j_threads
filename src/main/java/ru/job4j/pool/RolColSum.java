package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        int sumRow = 0;
        int sumCol = 0;
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                sumRow += matrix[i][j];
                sumCol += matrix[j][i];
            }
            result[i] = new Sums(sumRow, sumCol);
            sumRow = 0;
            sumCol = 0;
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            final int iF = i;
            result[i] = CompletableFuture.supplyAsync(() -> {
                int sumRow = 0;
                int sumCol = 0;
                for (int j = 0; j < matrix.length; j++) {
                    sumRow += matrix[iF][j];
                    sumCol += matrix[j][iF];
                }
                return new Sums(sumRow, sumCol);
            }).get();
        }
        return result;
    }
}
