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

    public Vertex(Position position, int floor) {
        super(position, floor);
    }

    public Vertex(PointF position, int floor) {
        super(position, floor);
    }

    public Vertex(int x, int y, int floor) {
        super(x, y, floor);
    }

    public Vertex(Vertex copy){
        super(copy);
    }


}
