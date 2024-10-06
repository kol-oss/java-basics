package edu.testing;

// Number = 27
// C5 = 2
// C7 = 6 => float
// C11 = 5

public class Main {
    private final static int SIZE = 3;

    public static void main(String[] args) {
        Matrix a = new Matrix(SIZE);
        Matrix.fillWithRandom(a);

        System.out.println("=== MATRIX A ===");
        System.out.println(a);

        Matrix b = new Matrix(SIZE);
        Matrix.fillWithRandom(b);

        System.out.println("=== MATRIX B ===");
        System.out.println(b);

        Matrix c = Executor.getMatrix(a, b);

        System.out.println("=== MATRIX C = A + B ===");
        System.out.println(c);

        Executor.count(c);
    }
}
