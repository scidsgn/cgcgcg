package com.scidsgn.cgcgcg.app.ex2;

import com.scidsgn.cgcgcg.gui.AppFrame;
import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Scene2;

import javax.swing.*;
import java.io.File;

public class AppEntry {
    public static void main(String[] args) {
        Scene2 scene = new Scene2();
        CameraCoordinates cameraCoordinates = new CameraCoordinates(new Vector(), 2.2);
        cameraCoordinates.getOrigin().set(0, 0, 7);
        cameraCoordinates.pitch(Math.PI);

        AppState state = new AppState(scene, cameraCoordinates);
        state.loadScene(new File("modes/hall.obj"));

        SwingUtilities.invokeLater(() -> {
            new AppFrame(state);
        });
    }
}
