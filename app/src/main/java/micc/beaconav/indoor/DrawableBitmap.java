package micc.beaconav.indoor;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import micc.beaconav.indoor.localization.Point;

/**
 * Created by Nagash on 23/12/2014.
 */
public class DrawableBitmap extends Drawable
{

    private Bitmap _bmp;


    public DrawableBitmap(Bitmap bmp, Point position, long zIndex)
    {
        super(position, zIndex);
        this._bmp = new
    }

    @Override
    public void draw(Canvas canvas){
        canvas.drawBitmap(_bmp, this.getX(), this.getY(), null );
    }

    @Override
    public float getWidth() {
        return _bmp.getWidth();
    }

    @Override
    public float getHeight() {
        return _bmp.getHeight();
    }


}
