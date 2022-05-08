package com.scidsgn.cgcgcg.loader;

import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.Mesh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OBJLoader {
    public static Mesh load(File file) {
        Mesh mesh = new Mesh();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader);

            String line;

            while ((line = reader.readLine()) != null) {
                String[] items = line.trim().split(" ");

                if (items[0].equals("v") && items.length >= 4) {
                    mesh.add(
                            new Vector(
                                    Double.parseDouble(items[1]),
                                    Double.parseDouble(items[2]),
                                    Double.parseDouble(items[3])
                            )
                    );
                } else if (items[0].equals("f") && items.length > 1) {
                    List<Integer> indices = new ArrayList<>();

                    for (int i = 1; i < items.length; i++) {
                        String[] is = items[i].split("/");

                        indices.add(Integer.parseInt(is[0]));
                    }

                    for (int i = 0; i < indices.size(); i++) {
                        mesh.connect(
                                indices.get(i) - 1,
                                indices.get((i + 1) % indices.size()) - 1
                        );
                    }
                }
            }

            fileReader.close();
        } catch (IOException ignored) {}

        return mesh;
    }
}
