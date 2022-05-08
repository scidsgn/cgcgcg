package com.scidsgn.cgcgcg.gui.ex1;

import com.scidsgn.cgcgcg.app.ex1.AppState;
import com.scidsgn.cgcgcg.loader.OBJLoader;
import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.CameraCoordinates;
import com.scidsgn.cgcgcg.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class AppFrame extends JFrame {
    private final AppState state;
    private final AppPanel panel;

    public AppFrame(AppState state) {
        super("CGCGCG1 - V: 0, E: 0");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);
        setResizable(false);

        this.state = state;
        this.panel = new AppPanel(state);
        prepareUI();

        addKeyListener(new AppListener(this, state, panel));

        pack();
    }

    void prepareUI() {
        add(BorderLayout.CENTER, panel);
    }

    private record AppListener(
            AppFrame frame, AppState state, AppPanel panel
    ) implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                // MOTION
                case KeyEvent.VK_A -> {
                    state.getCameraCoordinates().move(-0.1, 0, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_D -> {
                    state.getCameraCoordinates().move(0.1, 0, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_W -> {
                    state.getCameraCoordinates().move(0, 0, 0.1);
                    panel.renderScene();
                }
                case KeyEvent.VK_S -> {
                    state.getCameraCoordinates().move(0, 0, -0.1);
                    panel.renderScene();
                }
                case KeyEvent.VK_Q -> {
                    state.getCameraCoordinates().move(0, 0.1, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_E -> {
                    state.getCameraCoordinates().move(0, -0.1, 0);
                    panel.renderScene();
                }
                // ROTATION
                case KeyEvent.VK_J -> {
                    state.getCameraCoordinates().yaw(-0.04);
                    panel.renderScene();
                }
                case KeyEvent.VK_L -> {
                    state.getCameraCoordinates().yaw(0.04);
                    panel.renderScene();
                }
                case KeyEvent.VK_I -> {
                    state.getCameraCoordinates().pitch(0.04);
                    panel.renderScene();
                }
                case KeyEvent.VK_K -> {
                    state.getCameraCoordinates().pitch(-0.04);
                    panel.renderScene();
                }
                case KeyEvent.VK_U -> {
                    state.getCameraCoordinates().roll(-0.04);
                    panel.renderScene();
                }
                case KeyEvent.VK_O -> {
                    state.getCameraCoordinates().roll(0.04);
                    panel.renderScene();
                }
                // ZOOM
                case KeyEvent.VK_R -> {
                    state.getCameraCoordinates().zoomOut();
                    panel.renderScene();
                }
                case KeyEvent.VK_T -> {
                    state.getCameraCoordinates().zoomIn();
                    panel.renderScene();
                }
                // LOAD MODEL
                case KeyEvent.VK_1 -> {
                    JFileChooser chooser = new JFileChooser();
                    if (chooser.showOpenDialog(panel) == JFileChooser.APPROVE_OPTION) {
                        File modelFile = chooser.getSelectedFile();
                        
                        Scene scene = state.getScene();
                        scene.getMeshes().clear();
                        scene.getMeshes().add(OBJLoader.load(modelFile));


                        CameraCoordinates cameraCoordinates = new CameraCoordinates(new Vector(), 2.8);
                        cameraCoordinates.getOrigin().set(0, 0, 7);
                        cameraCoordinates.pitch(Math.PI);
                        state.setCameraCoordinates(cameraCoordinates);

                        panel.renderScene();

                        frame.setTitle(
                                String.format("CGCGCG1 - V: %d, E: %d", scene.countVertices(), scene.countEdges())
                        );
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }
}
