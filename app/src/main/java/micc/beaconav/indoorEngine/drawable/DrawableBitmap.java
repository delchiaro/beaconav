package micc.beaconav.indoorEngine.drawable;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PointF;


/**
 * Created by Nagash on 23/12/2014.
 */
public class DrawableBitmap extends DrawableImage
{

    private android.graphics.Bitmap _bmp;


    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex, PointF position, ReferencePoint refPoint){
        super(zIndex, position, refPoint);
        this._bmp = bmp;
    }
    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex, PointF position){
        super(zIndex ,position);
        this._bmp = bmp;
    }
    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex, ReferencePoint refPoint){
        super(zIndex, refPoint);
        this._bmp = bmp;
    }
    public DrawableBitmap(android.graphics.Bitmap bmp, long zIndex){
        super(zIndex);
        this._bmp = bmp;
    }


    @Override
    protected void _coreDraw(Canvas canvas, PointF position)
    {
        if(this._bmp != null && this.getPosition() != null)
            canvas.drawBitmap(_bmp, position.x + this.getUpLeftCorner().x, position.y + this.getUpLeftCorner().y, null );
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
