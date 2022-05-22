package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MeshFace {
    private final List<Vector> vertices;
    private final List<Vector> processedVertices;

    private final Color color;

    public MeshFace(Color color, List<Vector> vertices) {
        this.vertices = vertices;
        this.processedVertices = new ArrayList<>();
        this.color = color;

        for (int i = 0; i < vertices.size(); i++) {
            this.processedVertices.add(null);
        }
    }

    public Color getColor() {
        return color;
    }

    public List<Vector> getVertices() {
        return vertices;
    }

    public List<Vector> getProcessedVertices() {
        return processedVertices;
    }

    public double getMaxCoord(List<Vector> verticesToSearch, int index) {
        if(index > 2){
            throw new IndexOutOfBoundsException("Index for coordinates in vertices is wrong: {index}. Out of 0-2 bounds.");
        }
        double max;

        switch (index) {
            case 0 -> {
                max = verticesToSearch.get(0).getX();
                for (Vector v : verticesToSearch) {
                    if (v.getX() > max) {
                        max = v.getX();
                    }
                }
            }
            case 1 -> {
                max = verticesToSearch.get(0).getY();
                for (Vector v : verticesToSearch) {
                    if (v.getY() > max) {
                        max = v.getY();
                    }
                }
            }
            case 2 -> {
                max = verticesToSearch.get(0).getZ();
                for (Vector v : verticesToSearch) {
                    if (v.getZ() > max) {
                        max = v.getZ();
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + index);
        }

        return max;
    }

    public Vector getCentroid() {
        Vector sumVectors = new Vector(0, 0, 0);
        for (Vector v:processedVertices){
            sumVectors = Vector.add(sumVectors, v);
        }

        int l = processedVertices.size();
        sumVectors.set(sumVectors.getX()/l, sumVectors.getY()/l, sumVectors.getZ()/l);
        return sumVectors;
    }

    public double getMinCoord(List<Vector> verticesToSearch, int index) {
        if (index > 2) {
            throw new IndexOutOfBoundsException("Index for coordinates in vertices is wrong: {index}. Out of 0-2 bounds.");
        }
        double min;

        switch (index) {
            case 0 -> {
                min = verticesToSearch.get(0).getX();
                for (Vector v : verticesToSearch) {
                    if (v.getX() < min) {
                        min = v.getX();
                    }
                }
            }
            case 1 -> {
                min = verticesToSearch.get(0).getY();
                for (Vector v : verticesToSearch) {
                    if (v.getY() < min) {
                        min = v.getY();
                    }
                }
            }
            case 2 -> {
                min = verticesToSearch.get(0).getZ();
                for (Vector v : verticesToSearch) {
                    if (v.getZ() < min) {
                        min = v.getZ();
                    }
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + index);
        }

        return min;
    }

    public Vector getOriginalNormal() {
        return Vector.cross(
                Vector.sub(this.vertices.get(2), this.vertices.get(1)),
                Vector.sub(this.vertices.get(0), this.vertices.get(1))
        ).normal();
    }

    public Vector getNormal() {
        return Vector.cross(
                Vector.sub(this.processedVertices.get(2), this.processedVertices.get(1)),
                Vector.sub(this.processedVertices.get(0), this.processedVertices.get(1))
        ).normal();
    }
}
