package com.scidsgn.cgcgcg.gui;

import com.scidsgn.cgcgcg.app.AppStateLike;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AppPanel extends JPanel {
    private final AppStateLike state;

    private final BufferedImage buffer;

    public AppPanel(AppStateLike state) {
        setPreferredSize(new Dimension(600, 600));

        this.state = state;
        this.buffer = new BufferedImage(600, 600, BufferedImage.TYPE_INT_ARGB);

        renderScene();
    }

    public void renderScene() {
        Graphics2D gfx = this.buffer.createGraphics();

        this.state.getRenderer().render(gfx, this.buffer.getWidth(), this.buffer.getHeight());

        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(buffer, 0, 0, null);
    }
}
