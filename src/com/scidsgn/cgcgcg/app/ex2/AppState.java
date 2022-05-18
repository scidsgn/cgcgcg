package com.scidsgn.cgcgcg.app.ex2;

import com.scidsgn.cgcgcg.app.AppStateLike;
import com.scidsgn.cgcgcg.render.PolyRenderer;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Scene2;

import java.io.File;

public class AppState implements AppStateLike {
    private final Scene2 scene;
    private final CameraCoordinates cameraCoordinates;

    private final PolyRenderer renderer;

    public AppState(Scene2 scene, CameraCoordinates cameraCoordinates) {
        this.scene = scene;
        this.cameraCoordinates = cameraCoordinates;

        this.renderer = new PolyRenderer(scene, cameraCoordinates);
    }

    public CameraCoordinates getCameraCoordinates() {
        return cameraCoordinates;
    }

    public Scene2 getScene() {
        return scene;
    }

    public PolyRenderer getRenderer() {
        return renderer;
    }

    public void loadScene(File sceneFile) {
        scene.getMeshes().clear();
        // TODO: OBJLoader.load2()
        //scene.getMeshes().add(OBJLoader.load(sceneFile));
    }

    public String getSceneMetrics() {
        return String.format("F: %d", scene.countFaces());
    }
}
