package micc.beaconav.indoor.drawable;

import micc.beaconav.indoor.localization.Position;

/**
 * 
 */
public abstract class DrawableImage extends Drawable
{
    private Position _position;
    private ReferencePoint _referencePoint;


    public DrawableImage(long zIndex, Position position, ReferencePoint refPoint){
        super(zIndex);
        this._position = position.clone();
        this._referencePoint = refPoint;
    }
    public DrawableImage(long zIndex, ReferencePoint refPoint){
        this(zIndex, new Position(0,0), refPoint);
    }
    public DrawableImage(long zIndex, Position position){
        this(zIndex, position, ReferencePoint.UP_LEFT_CORNER);
    }



    public abstract float getWidth();
    public abstract float getHeight();


    public Position getPosition(){
        return _position;
    }
    public void setPosition(Position position) {
        this._position = position;
    }


    public ReferencePoint getReferencePoint(){
        return _referencePoint;
    }
    public Position getCenter()
    {
        if(this._referencePoint == ReferencePoint.UP_LEFT_CORNER)
        {
            return new Position(this._position.X() + getWidth()/2, this._position.Y() + getHeight()/2);
        }
        else if(this._referencePoint == ReferencePoint.CENTER)
        {
            return this._position;
        }
        else return null;
    }
    public Position getUpLeftCorner()
    {
        if(this._referencePoint == ReferencePoint.UP_LEFT_CORNER)
        {
            return this._position;
        }
        else if(this._referencePoint == ReferencePoint.CENTER)
        {
            return new Position(this._position.X() - getWidth()/2, this._position.Y() - getHeight()/2);
        }
        else return null;
    }


    public enum ReferencePoint
    {
        UP_LEFT_CORNER, CENTER;
    }
}