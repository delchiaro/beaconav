package micc.beaconav.indoorEngine.building.spot.draw;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import micc.beaconav.R;
import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.indoorEngine.building.spot.Spot;

/**
 * Created by Nagash on 29/12/2014.
 */
public abstract class DrawableSpot extends Spot
{
//    private Bitmap _bitmap;
    private final Drawable _drawable;

    public DrawableSpot() { this(0,0); }
    public DrawableSpot(float x, float y) {
        this(x, y, null);
    }
    public DrawableSpot(float x, float y, Room room_container) {
        super(x, y, room_container);
        this._drawable = generateDrawable();
    }


    public Drawable drawable(){ return this._drawable; }

//    public Bitmap bitmap() { return _bitmap; }

//    public BitmapDrawable bitmapDrawable(Resources resources) {
//        return new BitmapDrawable(resources, _bitmap);
//    }
//
//    @Deprecated
//    public BitmapDrawable bitmapDrawable() {
//        return new BitmapDrawable( _bitmap);
//    }


    protected abstract Drawable generateDrawable();

//    public final void refreshBitmap() {
//
//        _bitmap = Bitmap.createBitmap(300, 300, Bitmap.Config.ARGB_8888);
//        Canvas tempCanvas = new Canvas(_bitmap);
//        _drawable.draw(tempCanvas);
//    }

}
