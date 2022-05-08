package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Matrix3x3;
import com.scidsgn.cgcgcg.math.MatrixVector;
import com.scidsgn.cgcgcg.math.Vector;

public class CameraCoordinates {
    private double focalLength;

    private final Matrix3x3 matrix;
    private final Vector origin;

    private final MatrixVector cameraX;
    private final MatrixVector cameraY;
    private final MatrixVector cameraZ;

    public CameraCoordinates(Vector origin, double focalLength) {
        this.focalLength = focalLength;
        this.origin = origin;

        this.matrix = Matrix3x3.eye();
        this.cameraX = new MatrixVector(this.matrix, 0);
        this.cameraY = new MatrixVector(this.matrix, 1);
        this.cameraZ = new MatrixVector(this.matrix, 2);
    }

    public Vector project(Vector worldVector) {
        Vector local = Vector.matrix(Vector.sub(worldVector, origin), matrix);

        double scale = focalLength / local.getZ();
        Vector scaleV = new Vector(scale, scale, 1);

        return Vector.scale(local, scaleV);
    }

    public double getFocalLength() {
        return focalLength;
    }

    public void setFocalLength(double focalLength) {
        this.focalLength = focalLength;
    }

    public void zoomOut() {
        focalLength = Math.max(0.4, focalLength - 0.2);
    }

    public void zoomIn() {
        focalLength = Math.min(5, focalLength + 0.2);
    }

    public Vector getOrigin() {
        return origin;
    }

    public MatrixVector getCameraX() {
        return cameraX;
    }

    public MatrixVector getCameraY() {
        return cameraY;
    }

    public MatrixVector getCameraZ() {
        return cameraZ;
    }

    public void move(double dx, double dy, double dz) {
        Vector dxVec = Vector.scale(cameraX, dx);
        Vector dyVec = Vector.scale(cameraY, dy);
        Vector dzVec = Vector.scale(cameraZ, dz);

        origin.set(Vector.add(origin, dxVec, dyVec, dzVec));
    }

    public void roll(double angle) {
        Vector x = Vector.rotate(cameraX, cameraZ, angle);
        matrix.set(0, 0, x.getX());
        matrix.set(0, 1, x.getY());
        matrix.set(0, 2, x.getZ());

        Vector y = Vector.rotate(cameraY, cameraZ, angle);
        matrix.set(1, 0, y.getX());
        matrix.set(1, 1, y.getY());
        matrix.set(1, 2, y.getZ());
    }

    public void pitch(double angle) {
        Vector y = Vector.rotate(cameraY, cameraX, angle);
        matrix.set(1, 0, y.getX());
        matrix.set(1, 1, y.getY());
        matrix.set(1, 2, y.getZ());

        Vector z = Vector.rotate(cameraZ, cameraX, angle);
        matrix.set(2, 0, z.getX());
        matrix.set(2, 1, z.getY());
        matrix.set(2, 2, z.getZ());
    }

    public void yaw(double angle) {
        Vector x = Vector.rotate(cameraX, cameraY, angle);
        matrix.set(0, 0, x.getX());
        matrix.set(0, 1, x.getY());
        matrix.set(0, 2, x.getZ());

        Vector z = Vector.rotate(cameraZ, cameraY, angle);
        matrix.set(2, 0, z.getX());
        matrix.set(2, 1, z.getY());
        matrix.set(2, 2, z.getZ());
    }
}
