package micc.beaconav.indoorEngine.building;

import micc.beaconav.indoorEngine.building.ConvexArea;

/**
 * Created by Nagash on 29/12/2014.
 */
public interface ISpot
{
    public float getX();
    public float getY();
//    public int getFloor();
//
//    public Position getPosition();
//    public IndoorPosition getIndoorPosition();

    public ConvexArea getConvexAreaContainer();

}
