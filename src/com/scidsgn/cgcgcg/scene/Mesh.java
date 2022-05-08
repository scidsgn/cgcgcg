package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Mesh {
    private final List<Vector> vertices;
    private final List<MeshEdge> edges;

    public Mesh() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    public List<MeshEdge> getEdges() {
        return edges;
    }

    public List<Vector> getVertices() {
        return vertices;
    }

    public void add(Vector ...vs) {
        Collections.addAll(vertices, vs);
    }

    public void connect(int startIndex, int endIndex) {
        edges.add(
                new MeshEdge(
                        startIndex % vertices.size(),
                        endIndex % vertices.size()
                )
        );
    }

    public static record MeshEdge(int startIndex, int endIndex) {}
}
