package micc.beaconav.openglTest;


import java.util.List;

/**
 * Created by nagash on 09/12/14.
 */
public abstract class Shape
{

    static final int COORDS_PER_VERTEX = 3;
    private final int vertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    List<Vertex> vertexList;

    public abstract int getVertexCount();


}
