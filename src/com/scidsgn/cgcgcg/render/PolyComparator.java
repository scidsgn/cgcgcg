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
        return orderByZ(o1, o2);
    }

    private int compareProjectedCenter(MeshFace o1, MeshFace o2) {
        //A + dot(AP,AB) / dot(AB,AB) * AB

        Vector o1Centroid = o1.getCentroid();
        Vector o2Centroid = o2.getCentroid();

        Vector normal = o1.getNormal();
        Vector projectedO2Centroid;
        double dot = Vector.dot(Vector.add(o1Centroid, o2Centroid), o1.getNormal());

        projectedO2Centroid = Vector.add(o1Centroid, Vector.mulByScalar(normal, dot) );

        if(projectedO2Centroid.getZ() <  o1Centroid.getZ())
            return -1;
        else return 1;
    }

    private int orderByZ(MeshFace o1, MeshFace o2) {
        return Double.compare(o2.getCentroid().getZ(), o1.getCentroid().getZ());
    }

    private boolean overlapZRanges(MeshFace o1, MeshFace o2) {
        return o1.getMinCoord(o1.getProcessedVertices(), 2) < o2.getMaxCoord(o2.getProcessedVertices(), 2)
                && o2.getMinCoord(o2.getProcessedVertices(), 2) < o1.getMaxCoord(o1.getProcessedVertices(), 2);
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
