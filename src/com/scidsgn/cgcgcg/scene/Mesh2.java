package com.scidsgn.cgcgcg.scene;

import java.util.ArrayList;
import java.util.List;

public class Mesh2 {
    private final List<MeshFace> faces;

    public Mesh2() {
        this.faces = new ArrayList<>();
    }

    public List<MeshFace> getFaces() {
        return faces;
    }
}
