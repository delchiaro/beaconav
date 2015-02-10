package micc.beaconav.indoorEngine.drawable;

import android.graphics.PointF;

import micc.beaconav.localization.Position;

/**
 * 
 */
public abstract class DrawableImage extends Drawable
{
    private PointF _position;
    private ReferencePoint _referencePoint;


    public DrawableImage(long zIndex, PointF position, ReferencePoint refPoint){
        super(zIndex);
        this._position = position;
        this._referencePoint = refPoint;
    }
    public DrawableImage(long zIndex, ReferencePoint refPoint){
        this(zIndex, new PointF(0,0), refPoint);
    }
    public DrawableImage(long zIndex, PointF position){
        this(zIndex, position, ReferencePoint.UP_LEFT_CORNER);
    }
    public DrawableImage(long zIndex){
        this(zIndex, new PointF(0,0), ReferencePoint.UP_LEFT_CORNER);
    }



    public abstract float getWidth();
    public abstract float getHeight();


    public void setPosition(PointF position) {
        this._position = position;
    }
    public PointF getPosition() {
        return this._position;
    }


    public ReferencePoint getReferencePoint(){
        return _referencePoint;
    }
    public PointF getCenter()
    {
        if(this._referencePoint == ReferencePoint.UP_LEFT_CORNER)
        {
            return new PointF(this._position.x + getWidth()/2, this._position.y + getHeight()/2);
        }
        else if(this._referencePoint == ReferencePoint.CENTER)
        {
            return this._position;
        }
        else return null;
    }
    public PointF getUpLeftCorner()
    {
        if(this._referencePoint == ReferencePoint.UP_LEFT_CORNER)
        {
            return this._position;
        }
        else if(this._referencePoint == ReferencePoint.CENTER)
        {
            return new PointF(this._position.x - getWidth()/2, this._position.y - getHeight()/2);
        }
        else return null;
    }


    public enum ReferencePoint
    {
        UP_LEFT_CORNER, CENTER;
    }
}