package com.scidsgn.cgcgcg.gui;

import com.scidsgn.cgcgcg.app.AppStateLike;
import com.scidsgn.cgcgcg.app.ex1.AppState;
import com.scidsgn.cgcgcg.loader.OBJLoader;
import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.Scene;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

public class AppFrame extends JFrame {
    private final AppStateLike state;
    private final AppPanel panel;

    public AppFrame(AppStateLike state) {
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
            AppFrame frame, AppStateLike state, AppPanel panel
    ) implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            double moveDelta = e.isShiftDown() ? 0.35 : 0.1;
            double rotateDelta = e.isShiftDown() ? 0.15 : 0.04;

            switch (e.getKeyCode()) {
                // MOTION
                case KeyEvent.VK_A -> {
                    state.getCameraCoordinates().move(-moveDelta, 0, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_D -> {
                    state.getCameraCoordinates().move(moveDelta, 0, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_W -> {
                    state.getCameraCoordinates().move(0, 0, moveDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_S -> {
                    state.getCameraCoordinates().move(0, 0, -moveDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_Q -> {
                    state.getCameraCoordinates().move(0, moveDelta, 0);
                    panel.renderScene();
                }
                case KeyEvent.VK_E -> {
                    state.getCameraCoordinates().move(0, -moveDelta, 0);
                    panel.renderScene();
                }
                // ROTATION
                case KeyEvent.VK_J -> {
                    state.getCameraCoordinates().yaw(-rotateDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_L -> {
                    state.getCameraCoordinates().yaw(rotateDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_I -> {
                    state.getCameraCoordinates().pitch(rotateDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_K -> {
                    state.getCameraCoordinates().pitch(-rotateDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_U -> {
                    state.getCameraCoordinates().roll(-rotateDelta);
                    panel.renderScene();
                }
                case KeyEvent.VK_O -> {
                    state.getCameraCoordinates().roll(rotateDelta);
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

                        state.loadScene(modelFile);

                        state.getCameraCoordinates().reset(new Vector(0, 0, 7), 2.2);
                        state.getCameraCoordinates().pitch(Math.PI);

                        panel.renderScene();

                        frame.setTitle(
                                String.format("CGCGCG1 - %s", state.getSceneMetrics())
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
