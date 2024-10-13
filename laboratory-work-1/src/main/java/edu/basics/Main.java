package edu.basics;

// Number = 27
// C5 = 2
// C7 = 6 => float
// C11 = 5

public class Main {
    private final static int SIZE = 4;

    private static void runOperations(Matrix a, Matrix b) {
        Matrix c = Executor.getMatrix(a, b);

        System.out.println("=== MATRIX C = A + B ===");
        System.out.println(c);

        Executor.count(c);
    }

    public static void runWithDefinedValues() {
        Matrix a = new Matrix(SIZE);
        a.set(0, 0, 1.5f);
        a.set(1, 0, 2.5f);
        a.set(0, 2, 3.5f);
        a.set(3, 3, 4.5f);

        System.out.println("=== MATRIX A ===");
        System.out.println(a);

        Matrix b = new Matrix(SIZE);
        b.set(0, 0, 1.5f);
        b.set(0, 1, 2.5f);
        b.set(2, 0, 3.5f);
        b.set(0, 3, 4.5f);

        System.out.println("=== MATRIX B ===");
        System.out.println(b);

        runOperations(a, b);
    }

    public static void runWithRandomValues() {
        Matrix a = new Matrix(SIZE);
        Matrix.fillWithRandom(a);

        System.out.println("=== MATRIX A ===");
        System.out.println(a);

        Matrix b = new Matrix(SIZE);
        Matrix.fillWithRandom(b);

        System.out.println("=== MATRIX B ===");
        System.out.println(b);

        runOperations(a, b);
    }

    public static void main(String[] args) {
        System.out.println("===== RUN WITH DEFINED VALUES =====");
        runWithDefinedValues();

        System.out.println("---------------");

        System.out.println("===== RUN WITH RANDOM VALUES =====");
        runWithRandomValues();
    }
}
