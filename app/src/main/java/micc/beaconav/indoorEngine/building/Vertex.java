package micc.beaconav.indoorEngine.building;

import android.graphics.PointF;

/**
 * Created by nagash on 27/01/15.
 */
public class Vertex
{
    private PointF floorOffsetPosition;

    public Vertex(float x, float y) {
        this.floorOffsetPosition = new PointF(x, y);
    }

    public Vertex(PointF position) {
        this(position.x, position.y);
    }

    public float getX() {
        return floorOffsetPosition.x;
    }
    public float getY() {
        return floorOffsetPosition.y;
    }


}
