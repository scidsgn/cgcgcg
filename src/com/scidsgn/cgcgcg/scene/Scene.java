package com.scidsgn.cgcgcg.scene;

import com.scidsgn.cgcgcg.math.Vector;

import java.util.ArrayList;
import java.util.List;

public class Scene {
    private final CameraCoordinates cameraCoordinates;
    private final List<Mesh> meshes;

    public Scene() {
        this.cameraCoordinates = new CameraCoordinates(new Vector(), 0.35);
        this.meshes = new ArrayList<>();
    }

    public CameraCoordinates getCameraCoordinates() {
        return cameraCoordinates;
    }

    public List<Mesh> getMeshes() {
        return meshes;
    }

    public int countVertices() {
        return meshes.stream().mapToInt(mesh -> mesh.getVertices().size()).sum();
    }

    public int countEdges() {
        return meshes.stream().mapToInt(mesh -> mesh.getEdges().size()).sum();
    }
}
