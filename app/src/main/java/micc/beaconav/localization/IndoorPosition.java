package micc.beaconav.localization;

import android.graphics.PointF;

/**
 * Created by Nagash on 23/12/2014.
 */
public class IndoorPosition extends APosition
{
    private Position _position;
    private int _floor;


    public IndoorPosition(Position position, int floor){
        this._position = position;
        this._floor = floor;
    }
    public IndoorPosition(PointF point, int floor){
        this._position = new Position(point);
        this._floor = floor;
    }
    public IndoorPosition(float x, float y, int floor){
        this._position = new Position( x, y );
        this._floor = floor;
    }
    public IndoorPosition(IndoorPosition copy){
        this(copy.X(), copy.Y(), copy._floor);
    }

    public int getFloor(){ return this._floor; }
    public Position getPosition(){ return _position; }

    public void setX(float x){ this._position.setX(x); }
    public void setY(float y){ this._position.setY(y); }
    public void setFloor(int newFloor){ this._floor = newFloor; }

    @Override
    public float X() {
        return _position.X();
    }
    @Override
    public float Y() {
        return _position.Y();
    }
    public int floor(){ return _floor; }

    @Override
    public IndoorPosition clone()
    {
        return new IndoorPosition(this);
    }

}