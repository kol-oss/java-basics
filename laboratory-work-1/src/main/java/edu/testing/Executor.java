package edu.testing;

import java.util.Arrays;

public class Executor {
    // C5: C = A+B
    public static Matrix getMatrix(Matrix a, Matrix b) {
        return Matrix.add(a, b);
    }

    private static float getBoundary(float[] arr, boolean isMax) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("The given array is null or empty");
        }

        float[] copy = arr.clone();

        Arrays.sort(copy);
        return isMax ? copy[copy.length - 1] : copy[0];
    }

    // C11: get sum of biggest elements in rows with odd index
    // C11: get sum of lowest elements in columns with even index
    public static void count(Matrix matrix) {
        if (matrix == null) {
            throw new IllegalArgumentException("The given matrix is null");
        }

        float sumOfMax = 0;
        float sumOfMin = 0;

        for (int i = 0; i < matrix.rows; i++) {
            if (i % 2 == 0) {
                float max = Executor.getBoundary(matrix.getRow(i), true);
                System.out.println("> Max element in row " + i + ": " + max);

                sumOfMax += max;
            } else {
                float min = Executor.getBoundary(matrix.getColumn(i), false);

                System.out.println("> Min element in column " + i + ": " + min);
                sumOfMin += min;
            }
        }

        System.out.println("Sum of biggest elements in rows with odd index: " + sumOfMax);
        System.out.println("Sum of lowest elements in columns with even index: " + sumOfMin);
    }
}
