package micc.beaconav.localization.beaconHelper;

import com.estimote.sdk.Beacon;

import java.util.List;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
 */
public interface BeaconBestProximityListener {

    public void OnNewBeaconBestProximity(Beacon bestProximity, Beacon oldBestProximity);

    public void OnNoneBeaconBestProximity( Beacon oldBestProximity);

}
