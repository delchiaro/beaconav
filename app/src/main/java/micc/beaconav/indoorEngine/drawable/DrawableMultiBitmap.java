package micc.beaconav.indoorEngine.drawable;

import android.graphics.Bitmap;
import android.graphics.PointF;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Nagash on 29/12/2014.
 */
public class DrawableMultiBitmap extends DrawableBitmap
{


    Map<Integer, Bitmap> _bitmapList = new HashMap<Integer, Bitmap>();

    public DrawableMultiBitmap(long zIndex, PointF position, ReferencePoint refPoint) {
        super(null, zIndex, position, refPoint);
    }

    public DrawableMultiBitmap(PointF position, long zIndex) {
        this(zIndex, position, ReferencePoint.UP_LEFT_CORNER);
    }

    public DrawableMultiBitmap(long zIndex, ReferencePoint refPoint) {
        this(zIndex, new PointF(0,0), refPoint);
    }


    public DrawableMultiBitmap addBitmap(Bitmap bmp, int key)
    {
        _bitmapList.put(key, bmp);
        return this;
    }

    public DrawableMultiBitmap remBitmap(int key)
    {
        _bitmapList.remove(key);
        return this;
    }
    public void setBitmap(int key)
    {
        Bitmap bmp = _bitmapList.get(key);
        if(bmp!=null)
        {
            super.setBitmap(bmp);
        }
    }
    public void setBitmap(Bitmap bmp)
    {
        if(this._bitmapList.containsValue(bmp));
            super.setBitmap(bmp);
    }


}
