package com.scidsgn.cgcgcg.render;

import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Mesh;
import com.scidsgn.cgcgcg.scene.Scene;

import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;

public class LineRenderer implements Renderer {
    private Scene scene;
    private CameraCoordinates cameraCoordinates;

    private double epsilon = 1e-3;

    public LineRenderer(Scene scene, CameraCoordinates cameraCoordinates) {
        this.scene = scene;
        this.cameraCoordinates = cameraCoordinates;
    }

    private void renderLine(
            Graphics2D gfx, int width, int height,
            Vector start, Vector end
    ) {
        if (start.getZ() < epsilon || end.getZ() < epsilon) {
            return;
        }

        double sx = (start.getX() + 1) * 0.5 * width;
        double sy = (start.getY() + 1) * 0.5 * height;
        double ex = (end.getX() + 1) * 0.5 * width;
        double ey = (end.getY() + 1) * 0.5 * height;

        gfx.drawLine((int)sx, (int)sy, (int)ex, (int)ey);
    }

    public void render(Graphics2D gfx, int width, int height) {
        gfx.clearRect(0, 0, width, height);

        for (Mesh mesh : scene.getMeshes()) {
            List<Vector> vertices = new ArrayList<>();

            for (Vector point : mesh.getVertices()) {
                vertices.add(cameraCoordinates.project(point));
            }

            for (Mesh.MeshEdge edge : mesh.getEdges()) {
                renderLine(
                        gfx, width, height,
                        vertices.get(edge.startIndex()), vertices.get(edge.endIndex())
                );
            }
        }
    }
}
