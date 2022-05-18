package com.scidsgn.cgcgcg.render;

import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.MeshFace;
import com.scidsgn.cgcgcg.scene.Scene2;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PolyRenderer implements Renderer {
    private final Scene2 scene;
    private final CameraCoordinates cameraCoordinates;

    private final double epsilon = 1e-3;

    public PolyRenderer(Scene2 scene, CameraCoordinates cameraCoordinates) {
        this.scene = scene;
        this.cameraCoordinates = cameraCoordinates;
    }

    private void projectFace(MeshFace face) {
        // TODO: hmm this!
    }

    private List<MeshFace> retrieveFaces() {
        // TODO: all this!
        // retrieve, project, eliminate, sort
        return new ArrayList<>();
    }

    private void renderFace(MeshFace face) {
        // TODO: this too...
    }

    public void render(Graphics2D gfx, int width, int height) {
        gfx.clearRect(0, 0, width, height);

        for (MeshFace face : retrieveFaces()) {
            renderFace(face);
        }
    }
}
