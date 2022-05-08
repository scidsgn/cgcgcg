package com.scidsgn.cgcgcg.math;

public class MatrixVector extends Vector {
    private final Matrix3x3 matrix;
    private final int row;

    public MatrixVector(Matrix3x3 matrix, int row) {
        this.matrix = matrix;
        this.row = row;
    }

    @Override
    public double getX() {
        return matrix.get(row, 0);
    }

    @Override
    public double getY() {
        return matrix.get(row, 1);
    }

    @Override
    public double getZ() {
        return matrix.get(row, 2);
    }

    @Override
    public void setX(double x) {
        matrix.set(row, 0, x);
    }

    @Override
    public void setY(double y) {
        matrix.set(row, 1, y);
    }

    @Override
    public void setZ(double z) {
        matrix.set(row, 2, z);
    }
}
