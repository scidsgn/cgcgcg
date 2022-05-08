package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeshFace {
    private final List<Vector> vertices;

    public MeshFace() {
        this.vertices = new ArrayList<>();
    }

    public List<Vector> getVertices() {
        return vertices;
    }

    public static MeshFace make(Vector ...vertices) {
        MeshFace face = new MeshFace();

        Collections.addAll(face.getVertices(), vertices);

        return face;
    }
}
