package micc.beaconav.indoorEngine.building.spot.marker;

import android.graphics.PointF;

/**
 * Created by nagash on 15/02/15.
 */
public class CollidableCircle implements Collidable {

    PointF p;
    float radius;

    public CollidableCircle(float x1, float y1, float radius)
    {
        p = new PointF(x1, y1);
        this.radius = radius;
    }

    @Override
    public boolean checkCollision(float x, float y) {

        double distance = Math.sqrt((x - p.x)*(x - p.x) + (y - p.y)*(y - p.y));
        if(distance<radius)
            return true;
        else return false;
    }
}
