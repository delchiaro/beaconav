package micc.beaconav.indoor.localization;

/**
 * Created by Nagash on 23/12/2014.
 */
public class Point
{
    private float _x;
    private float _y;

    public Point(float x, float y)
    {
        this._x = x;
        this._y = y;
    }
    public Point(Point copy)
    {
        this._x = copy._x;
        this._y = copy._y;
    }

    public float getX(){ return this._x; }
    public float getY(){ return this._y; }

    @Override
    public Point clone()
    {
        Point clone = new Point(this);
        return clone;
    }

}
