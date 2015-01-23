package micc.beaconav.indoor.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import micc.beaconav.localization.Position;

/**
 * Created by Nagash on 23/12/2014.
 */
public class DrawableBitmap extends DrawableImage
{

    private android.graphics.Bitmap _bmp;


    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex, Position position, ReferencePoint refPoint){
        super(zIndex, position, refPoint);
        this._bmp = bmp;
    }
    public DrawableBitmap(android.graphics.Bitmap bmp, Position position, long zIndex){
        super(zIndex ,position);
        this._bmp = bmp;
    }
    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex, ReferencePoint refPoint){
        super(zIndex, refPoint);
        this._bmp = bmp;
    }



    @Override
    protected void _coreDraw(Canvas canvas)
    {
        if(this._bmp != null && this.getPosition() != null)
            canvas.drawBitmap(_bmp, this.getUpLeftCorner().X(), this.getUpLeftCorner().Y(), null );
    }

    @Override
    public float getWidth() {
        return _bmp.getWidth();
    }

    @Override
    public float getHeight() {
        return _bmp.getHeight();
    }



    public void setBitmap(Bitmap bmp){
        this._bmp = bmp;
    }
    public Bitmap getBitmap(){
        return this._bmp;
    }

}
