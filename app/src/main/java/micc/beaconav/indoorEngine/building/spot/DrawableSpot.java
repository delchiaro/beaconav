package micc.beaconav.indoorEngine.building.spot;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

/**
 * Created by Nagash on 29/12/2014.
 */
public abstract class DrawableSpot extends Spot
{
    private final Drawable _drawable;

    public DrawableSpot() { this(0,0); }
    public DrawableSpot(float x, float y) {
        super(x, y);
        this._drawable = generateDrawable();
    }

    public Drawable drawable(){ return this._drawable; }


    protected abstract Drawable generateDrawable();

}
