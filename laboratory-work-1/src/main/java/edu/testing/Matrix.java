package edu.testing;

import java.util.Random;

public class Matrix {
    public final int rows;
    public final int cols;
    private final float[][] values;

    public Matrix(int size) {
        this.rows = size;
        this.cols = size;

        values = new float[rows][cols];
    }

    public Matrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Matrix rows and cols must be positive");
        }

        this.values = new float[rows][cols];

        this.rows = rows;
        this.cols = cols;
    }

    public static void fillWithRandom(Matrix matrix) {
        int rows = matrix.rows;
        int cols = matrix.cols;

        Random random = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix.set(i, j, random.nextFloat());
            }
        }
    }

    private void validate(int row, int col) {
        if (row < 0 || row >= this.rows || col < 0 || col >= this.cols) {
            throw new IndexOutOfBoundsException("Matrix coord is out of bounds");
        }
    }

    public float get(int row, int col) {
        this.validate(row, col);

        return values[row][col];
    }

    public float[] getRow(int index) {
        if (index < 0 || index >= this.rows) {
            throw new IndexOutOfBoundsException("Row index is out of bounds");
        }

        return values[index];
    }

    public float[] getColumn(int index) {
        if (index < 0 || index >= this.cols) {
            throw new IndexOutOfBoundsException("Column index is out of bounds");
        }

        float[] column = new float[this.cols];

        for (int j = 0; j < this.rows; j++) {
            column[j] = values[j][index];
        }

        return column;
    }

    public void set(int row, int col, float value) {
        this.validate(row, col);

        this.values[row][col] = value;
    }

    public static Matrix add(Matrix a, Matrix b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Matrix argument is null");
        }

        if (a.rows != b.rows || a.cols != b.cols) {
            throw new IllegalArgumentException("Matrix sizes must be equal");
        }

        int rows = a.rows, cols = a.cols;
        Matrix result = new Matrix(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.set(i, j, a.get(i, j) + b.get(i, j));
            }
        }

        return result;
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result.append(values[i][j]).append(" ");
            }

            result.append("\n");
        }

        return result.toString();
    }
}
