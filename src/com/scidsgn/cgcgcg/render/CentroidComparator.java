package com.scidsgn.cgcgcg.render;

import com.scidsgn.cgcgcg.scene.MeshFace;

public class CentroidComparator extends PolyComparator {

    @Override
    public int compare(MeshFace o1, MeshFace o2) {
        return orderByZ(o1, o2);
    }

    private int orderByZ(MeshFace o1, MeshFace o2) {
        return Double.compare(o2.getCentroid().getZ(), o1.getCentroid().getZ());
    }
}
