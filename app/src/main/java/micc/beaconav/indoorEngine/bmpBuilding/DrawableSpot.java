package micc.beaconav.indoorEngine.bmpBuilding;

import android.graphics.Canvas;
import android.graphics.PointF;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.bmpBuilding.Spot;

/**
 * Created by Nagash on 29/12/2014.
 */
public class DrawableSpot extends Spot
{
    private Drawable _drawable;


    public DrawableSpot(PointF position, Drawable drawable){
        super(position);
        this._drawable = drawable;
    }

    public void draw(Canvas canvas){
        this._drawable.draw(canvas, position);
    }


}
