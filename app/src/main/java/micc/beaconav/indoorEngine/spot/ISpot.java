package micc.beaconav.indoorEngine.spot;

import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

/**
 * Created by Nagash on 29/12/2014.
 */
public interface ISpot
{
    public float getX();
    public float getY();
    public int getFloor();

    public Position getPosition();
    public IndoorPosition getIndoorPosition();


}
