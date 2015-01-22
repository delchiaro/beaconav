package micc.beaconav.indoor.spot;

import micc.beaconav.indoor.localization.IndoorPosition;
import micc.beaconav.indoor.localization.Position;

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
