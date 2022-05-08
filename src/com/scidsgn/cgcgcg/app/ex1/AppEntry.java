package com.scidsgn.cgcgcg.app.ex1;

import com.scidsgn.cgcgcg.gui.ex1.AppFrame;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.Scene;
import javax.swing.*;

public class AppEntry {
    public static void main(String[] args) {
        Scene scene = new Scene();
        CameraCoordinates cameraCoordinates = new CameraCoordinates(new Vector(), 2.8);
        cameraCoordinates.getOrigin().set(0, 0, 7);
        cameraCoordinates.pitch(Math.PI);

        AppState state = new AppState(scene, cameraCoordinates);

        SwingUtilities.invokeLater(() -> {
            new AppFrame(state);
        });
    }
}
