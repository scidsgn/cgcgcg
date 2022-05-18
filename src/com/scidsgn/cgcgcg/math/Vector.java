package com.scidsgn.cgcgcg.math;

public class Vector {
    private double x;
    private double y;
    private double z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Vector() {
        this(0.0, 0.0, 0.0);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public void set(double x, double y, double z) {
        setX(x);
        setY(y);
        setZ(z);
    }

    public void set(Vector v) {
        set(v.getX(), v.getY(), v.getZ());
    }

    public double length() {
        return Math.sqrt(getX() * getX() + getY() * getY() + getZ() * getZ());
    }

    public Vector normal() {
        return Vector.scale(this, 1.0 / length());
    }

    public static Vector add(Vector a, Vector b) {
        return new Vector(
                a.getX() + b.getX(),
                a.getY() + b.getY(),
                a.getZ() + b.getZ()
        );
    }

    public static Vector add(Vector a, Vector ...vs) {
        Vector out = a;

        for (Vector v : vs) {
            out = Vector.add(out, v);
        }

        return out;
    }

    public static Vector sub(Vector a, Vector b) {
        return new Vector(
                a.getX() - b.getX(),
                a.getY() - b.getY(),
                a.getZ() - b.getZ()
        );
    }

    public static Vector scale(Vector v, Vector s) {
        return new Vector(v.getX() * s.getX(), v.getY() * s.getY(), v.getZ() * s.getZ());
    }

    public static Vector scale(Vector v, double s) {
        return scale(v, new Vector(s, s, s));
    }

    public static double dot(Vector a, Vector b) {
        return a.getX() * b.getX() +
               a.getY() * b.getY() +
               a.getZ() * b.getZ();
    }

    public static Vector cross(Vector a, Vector b) {
        return new Vector(
                a.getY() * b.getZ() - a.getZ() * b.getY(),
                a.getZ() * b.getX() - a.getX() * b.getZ(),
                a.getX() * b.getY() - a.getY() * b.getX()
        );
    }

    public static Vector rotate(Vector v, Vector axis, double angle) {
        double cosa = Math.cos(angle);
        double sina = Math.sin(angle);

        Vector t1 = scale(v, cosa);
        Vector t2 = scale(cross(axis, v), sina);
        Vector t3 = scale(axis, dot(axis, v) * (1 - cosa));

        return add(add(t1, t2), t3);
    }

    public static Vector matrix(Vector v, Matrix3x3 mx) {
        return new Vector(
                v.getX() * mx.get(0, 0) + v.getY() * mx.get(0, 1) + v.getZ() * mx.get(0, 2),
                v.getX() * mx.get(1, 0) + v.getY() * mx.get(1, 1) + v.getZ() * mx.get(1, 2),
                v.getX() * mx.get(2, 0) + v.getY() * mx.get(2, 1) + v.getZ() * mx.get(2, 2)
        );
    }

    @Override
    public String toString() {
        return String.format("Vector[ %g %g %g ]", getX(), getY(), getZ());
    }
}
