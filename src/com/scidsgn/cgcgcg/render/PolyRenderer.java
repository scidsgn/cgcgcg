package com.scidsgn.cgcgcg.render;

import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Mesh2;
import com.scidsgn.cgcgcg.scene.MeshFace;
import com.scidsgn.cgcgcg.scene.Scene2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        for (int i = 0; i < face.getVertices().size(); i++) {
            Vector vertex = face.getVertices().get(i);

            face.getProcessedVertices().set(i, cameraCoordinates.project(vertex));
        }
    }

    private List<MeshFace> retrieveFaces() {
        // TODO: all this!
        // retrieve, project, eliminate, sort
        List<MeshFace> faces = new ArrayList<>();

        for (Mesh2 mesh : scene.getMeshes()) {
            for (MeshFace face : mesh.getFaces()) {
                projectFace(face);

                if (
                        face.getMaxCoord(face.getProcessedVertices(), 2) < epsilon ||
                        face.getMinCoord(face.getProcessedVertices(), 2) < epsilon
                ) {
                    continue;
                }
                if (face.getNormal().getZ() > 0) {
                    continue;
                }

                faces.add(face);
            }
        }
        try {
            faces.sort(new PolyComparator());
        } catch (IllegalArgumentException e) {
            faces.sort(new CentroidComparator());
        }

        return faces;
    }

    private void renderFace(Graphics2D gfx, int width, int height, MeshFace face) {
        // TODO: this too...
        int n = face.getProcessedVertices().size();
        int[] xs = new int[n];
        int[] ys = new int[n];

        for (int i = 0; i < n; i++) {
            Vector vertex = face.getProcessedVertices().get(i);

            xs[i] = (int)((vertex.getX() + 1) * 0.5 * width);
            ys[i] = (int)((vertex.getY() + 1) * 0.5 * height);
        }

        Vector normal = face.getNormal();

        Color normalColor = new Color(
                (float)Math.max(Math.min(normal.getX() * 0.5 + 0.5, 1.0), 0.0),
                (float)Math.max(Math.min(normal.getY() * 0.5 + 0.5, 1.0), 0.0),
                (float)Math.max(Math.min(normal.getZ() * 0.5 + 0.5, 1.0), 0.0)
        );

        gfx.setPaint(face.getColor());
        gfx.fillPolygon(xs, ys, n);

    }

    public void render(Graphics2D gfx, int width, int height) {
        gfx.clearRect(0, 0, width, height);

        for (MeshFace face : retrieveFaces()) {
            renderFace(gfx, width, height, face);
        }
    }
}
