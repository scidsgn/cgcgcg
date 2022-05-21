package com.scidsgn.cgcgcg.loader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MTLLoader {
    private final List<MTLEntry> entries;

    private MTLLoader() {
        this.entries = new ArrayList<>();
    }

    public List<MTLEntry> getEntries() {
        return entries;
    }

    public Color getEntry(String name) {
        for (MTLEntry entry : entries) {
            if (entry.getName().equals(name)) {
                return entry.getColor();
            }
        }

        return null;
    }

    public static MTLLoader load(File file) {
        MTLLoader loader = new MTLLoader();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            MTLEntry current = null;

            while ((line = reader.readLine()) != null) {
                String[] items = line.trim().split(" ");

                if (items[0].equals("newmtl") && items.length >= 2) {
                    current = new MTLEntry(items[1]);
                    loader.getEntries().add(current);
                } else if (items[0].equals("Kd") && items.length >= 4 && current != null) {
                    Color color = new Color(
                            Float.parseFloat(items[1]),
                            Float.parseFloat(items[2]),
                            Float.parseFloat(items[3])
                    );
                    current.setColor(color);
                }
            }
        } catch (IOException ignored) {}

        return loader;
    }

    public static class MTLEntry {
        private final String name;
        private Color color;

        public MTLEntry(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }
    }
}
