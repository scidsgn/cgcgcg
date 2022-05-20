package com.scidsgn.cgcgcg.render;

import com.scidsgn.cgcgcg.math.Vector;
import com.scidsgn.cgcgcg.scene.MeshFace;

import java.util.Comparator;

public class PolyComparator implements Comparator<MeshFace> {
    @Override
    public int compare(MeshFace o1, MeshFace o2) {
        if (overlapXYBoundingBoxes(o1, o2)) {
            if(overlapZRanges(o1, o2)) {
                return compareProjectedCenter(o1, o2);
            }
            return orderByZ(o1, o2);
        }
        return 0;
    }

    private int compareProjectedCenter(MeshFace o1, MeshFace o2) {
        //TODO normal, project point, compare
        Vector normal = o1.getNormal();
        return 0;
    }

    private int orderByZ(MeshFace o1, MeshFace o2) {
        if ( o1.getMaxCoord(o1.getVertices(), 2) > o2.getMaxCoord(o1.getVertices(), 2) ) {
            return -1; //if o1 Z is further, it should go to the front in the list
        } else {
            return 1;
        }
    }

    private boolean overlapZRanges(MeshFace o1, MeshFace o2) {
        return o1.getMinCoord(o1.getProcessedVertices(), 2) <= o2.getMaxCoord(o2.getProcessedVertices(), 2)
                && o2.getMinCoord(o2.getProcessedVertices(), 2) <= o1.getMaxCoord(o1.getProcessedVertices(), 2);
    }

    private boolean overlapXYBoundingBoxes(MeshFace o1, MeshFace o2) {
        //x1 <= x4 && x3 <= x2 comparing X ranges
        if(o1.getMinCoord(o1.getProcessedVertices(), 0) <= o2.getMaxCoord(o2.getProcessedVertices(), 0)
                && o2.getMinCoord(o2.getProcessedVertices(), 0) <= o1.getMaxCoord(o1.getProcessedVertices(), 0)) {
            return true;
        }

        //comparing Y ranges
        if(o1.getMinCoord(o1.getProcessedVertices(), 1) <= o2.getMaxCoord(o2.getProcessedVertices(), 1)
                && o2.getMinCoord(o2.getProcessedVertices(), 1) <= o1.getMaxCoord(o1.getProcessedVertices(), 1)) {
            return true;
        }
        return false;
    }
}
