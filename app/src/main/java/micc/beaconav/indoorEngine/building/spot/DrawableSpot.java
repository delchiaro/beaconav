package micc.beaconav.indoorEngine.building.spot;

import android.graphics.Canvas;
import android.graphics.PointF;

import micc.beaconav.indoorEngine.building.ConvexArea;
import micc.beaconav.indoorEngine.building.Spot;
import micc.beaconav.indoorEngine.drawable.Drawable;

/**
 * Created by Nagash on 29/12/2014.
 */
public class DrawableSpot extends Spot
{
    private Drawable _drawable;


    public DrawableSpot(ConvexArea convexAreaContainer, float x, float y, Drawable drawable){
        super(convexAreaContainer, x, y);
        this._drawable = drawable;
    }
    public DrawableSpot(ConvexArea convexAreaContainer, PointF position,  Drawable drawable){
        this(convexAreaContainer, position.x, position.y, drawable);
    }

    public void draw(Canvas canvas){
        this._drawable.draw(canvas);
    }


}
