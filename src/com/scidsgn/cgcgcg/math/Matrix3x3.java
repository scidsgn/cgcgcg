package com.scidsgn.cgcgcg.math;

public class Matrix3x3 {
    private double[] data;

    public Matrix3x3() {
        this.data = new double[9];
    }

    private int index(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            throw new IllegalArgumentException("Cell index out of range.");
        }

        return row * 3 + col;
    }

    public double get(int row, int col) {
        return data[index(row, col)];
    }

    public void set(int row, int col, double value) {
        data[index(row, col)] = value;
    }

    public static Matrix3x3 eye() {
        Matrix3x3 matrix = new Matrix3x3();

        matrix.set(0, 0, 1.0);
        matrix.set(0, 1, 0.0);
        matrix.set(0, 2, 0.0);

        matrix.set(1, 0, 0.0);
        matrix.set(1, 1, 1.0);
        matrix.set(1, 2, 0.0);

        matrix.set(2, 0, 0.0);
        matrix.set(2, 1, 0.0);
        matrix.set(2, 2, 1.0);

        return matrix;
    }
}
