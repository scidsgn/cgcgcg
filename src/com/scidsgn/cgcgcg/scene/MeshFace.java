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
}
