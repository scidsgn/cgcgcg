package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class Scene2 {
    private final CameraCoordinates cameraCoordinates;
    private final List<Mesh2> meshes;

    public Scene2() {
        this.cameraCoordinates = new CameraCoordinates(new Vector(), 0.35);
        this.meshes = new ArrayList<>();
    }

    public CameraCoordinates getCameraCoordinates() {
        return cameraCoordinates;
    }

    public List<Mesh2> getMeshes() {
        return meshes;
    }

    public int countFaces() {
        return meshes.stream().mapToInt(mesh -> mesh.getFaces().size()).sum();
    }
}
