package com.scidsgn.cgcgcg.app.ex2;

import com.scidsgn.cgcgcg.loader.MTLLoader;

import java.io.File;

public class AppEntry {
    public static void main(String[] args) {
        MTLLoader loader = MTLLoader.load(new File("models/cubes.mtl"));

        System.out.println("Found MTL entries:");
        for (MTLLoader.MTLEntry entry : loader.getEntries()) {
            System.out.printf("%s: %s%n", entry.getName(), entry.getColor());
        }
    }
}
