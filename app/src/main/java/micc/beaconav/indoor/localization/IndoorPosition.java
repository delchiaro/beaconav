package micc.beaconav.indoor.localization;

/**
 * Created by Nagash on 23/12/2014.
 */
public class IndoorPosition extends Point
{
    private int _floor;

    public IndoorPosition(float x, float y, int floor)
    {
        super(x, y);
        this._floor = floor;
    }

    public float getFloor(){ return this._floor; }

}
