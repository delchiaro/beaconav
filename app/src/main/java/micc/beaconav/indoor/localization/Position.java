package micc.beaconav.indoor.localization;

import android.graphics.PointF;

/**
 * Created by Nagash on 23/12/2014.
 */
public class Position extends APosition
{
    private PointF _point;



    public Position(PointF point){
        this._point = point;
    }
    public Position(float x, float y){
        this._point = new PointF(x, y);
    }
    public Position(Position copy)
    {
        this(copy.X(), copy.Y());
    }

    @Override
    public float X(){ return this._point.x; }
    @Override
    public float Y(){ return this._point.y; }

    public void setX(float x){ this._point.x = x; }
    public void setY(float y){ this._point.y = y; }
    public PointF getPoint(){return this._point;}
    public PointF getPointClone(){ return new PointF(_point.x, _point.y); }
    public void setPoint(PointF newPointRef){ this._point = newPointRef; }
    public void setPointCopy(PointF point){ this._point.x = point.x; this._point.y = point.y; }


    @Override
    public Position clone()
    {
        return new Position(this);
    }




}
