package micc.beaconav.indoorEngine.spot;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

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
