package com.scidsgn.cgcgcg.app.ex1;

import com.scidsgn.cgcgcg.app.AppStateLike;
import com.scidsgn.cgcgcg.loader.OBJLoader;
import com.scidsgn.cgcgcg.render.LineRenderer;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Scene;

import java.io.File;

public class AppState implements AppStateLike {
    private final Scene scene;
    private CameraCoordinates cameraCoordinates;

    private final LineRenderer renderer;

    public AppState(Scene scene, CameraCoordinates cameraCoordinates) {
        this.scene = scene;
        this.cameraCoordinates = cameraCoordinates;

        this.renderer = new LineRenderer(scene, cameraCoordinates);
    }

    public CameraCoordinates getCameraCoordinates() {
        return cameraCoordinates;
    }

    public void setCameraCoordinates(CameraCoordinates cameraCoordinates) {
        this.cameraCoordinates = cameraCoordinates;
    }

    public LineRenderer getRenderer() {
        return renderer;
    }

    public Scene getScene() {
        return scene;
    }

    public void loadScene(File sceneFile) {
        scene.getMeshes().clear();
        scene.getMeshes().add(OBJLoader.load(sceneFile));
    }

    public String getSceneMetrics() {
        return String.format("V: %d, E: %d", scene.countVertices(), scene.countEdges());
    }
}
