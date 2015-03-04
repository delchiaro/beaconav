package micc.beaconav.indoorEngine.beaconHelper;

import com.estimote.sdk.Beacon;

import java.util.List;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface BeaconProximityListener {

    public void OnBeaconProximity(List<Beacon> proximityBeacons);


}
