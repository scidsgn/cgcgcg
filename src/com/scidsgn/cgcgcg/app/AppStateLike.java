package com.scidsgn.cgcgcg.app;

import com.scidsgn.cgcgcg.render.Renderer;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;

import java.io.File;

public interface AppStateLike {
    CameraCoordinates getCameraCoordinates();
    Renderer getRenderer();
    void loadScene(File sceneFile);
    String getSceneMetrics();
}
