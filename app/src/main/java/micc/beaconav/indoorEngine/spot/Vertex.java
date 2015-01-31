package micc.beaconav.indoorEngine.spot;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;

import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

/**
 * Created by nagash on 27/01/15.
 */
public class Vertex extends Spot
{

    public Vertex(Spot spot){
        super(spot);
    }
    public Vertex(IndoorPosition indPosition) {
        super(indPosition);
    }

    public Vertex(Position position) {
        super(position, Integer.MAX_VALUE);
    }

    public Vertex(PointF position) {
        super(position, Integer.MAX_VALUE);
    }

    public Vertex(float x, float y) {
        super(x, y, Integer.MAX_VALUE);
    }

    public Vertex(Vertex copy){
        super(copy);
    }


}
