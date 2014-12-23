package micc.beaconav.indoor;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

import micc.beaconav.indoor.localization.Point;

/**
 * 
 */
public abstract class Drawable
{
    private static long HigherZIndex = 0;

    private Point _position;
    private long _zIndex;


	public Drawable(Point position, long zIndex)
    {
        this._position = position.clone();
        this._zIndex = zIndex;

        if(zIndex > Drawable.HigherZIndex)
            Drawable.HigherZIndex = zIndex;
	}
    public Drawable(Point position)
    {
        this._position = position.clone();
        Drawable.HigherZIndex += 1;
        this._zIndex =  Drawable.HigherZIndex;
    }
    public Drawable(float x, float y, long zIndex)
    {
        this(new Point(x, y), zIndex);
    }
    public Drawable(float x, float y)
    {
        this( new Point(x, y) );
    }


    public abstract void draw(Canvas canvas);
    public abstract float getWidth();
    public abstract float getHeight();
    public long getZIndex()
    {
        return _zIndex;
    }
    public static long getHigherZIndex()
    {
        return Drawable.HigherZIndex;
    }


    public float getX()
    {
        return _position.getX();
    }
    public float getY()
    {
        return _position.getY();
    }

}