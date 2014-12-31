package micc.beaconav.indoor.drawable;

import android.graphics.Bitmap;

import java.util.HashMap;
import java.util.Map;

import micc.beaconav.indoor.localization.Position;

/**
 * Created by Nagash on 29/12/2014.
 */
public class DrawableMultiBitmap extends DrawableBitmap
{


    Map<Integer, Bitmap> _bitmapList = new HashMap<Integer, Bitmap>();

    public DrawableMultiBitmap(long zIndex, Position position, ReferencePoint refPoint) {
        super(null, zIndex, position, refPoint);
    }

    public DrawableMultiBitmap(Position position, long zIndex) {
        super(null, position, zIndex);
    }

    public DrawableMultiBitmap(long zIndex, ReferencePoint refPoint) {
        super(null, zIndex, refPoint);
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
