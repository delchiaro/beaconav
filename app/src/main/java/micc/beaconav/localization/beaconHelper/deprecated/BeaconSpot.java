package micc.beaconav.localization.beaconHelper.deprecated;

import com.estimote.sdk.Beacon;

import java.util.List;

import micc.beaconav.indoorEngine.beaconHelper.*;
import micc.beaconav.indoorEngine.building.spot.Spot;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public class BeaconSpot extends Beacon
{

    Spot _linkedSpot;

    public BeaconSpot(Spot linkedSpot, String proximityUUID, String name, String macAddress, int major, int minor, int measuredPower, int rssi)
    {
        super(proximityUUID, name, macAddress, major, minor, measuredPower, rssi);
        this._linkedSpot = linkedSpot;
    }

    public Spot getLinkedSpot(){
        return _linkedSpot;
    }



}
