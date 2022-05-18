package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeshFace {
    private final List<Vector> vertices;
    private final List<Vector> processedVertices;

    private final Color color;

    public MeshFace(Color color) {
        this.vertices = new ArrayList<>();
        this.processedVertices = new ArrayList<>();
        this.color = color;
    }

    public List<Vector> getVertices() {
        return vertices;
    }

    public List<Vector> getProcessedVertices() {
        return processedVertices;
    }

    public static MeshFace make(Color color, Vector ...vertices) {
        MeshFace face = new MeshFace(color);

        Collections.addAll(face.getVertices(), vertices);
        Collections.addAll(face.getProcessedVertices(), vertices);

        return face;
    }
}
