package micc.beaconav.localization.beaconHelper;

import com.estimote.sdk.Beacon;

import java.util.List;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface BeaconBestProximityListener {

    public void OnNewBeaconBestProximity(Beacon bestProximity, Beacon oldBestProximity);

    public void OnNoneBeaconBestProximity( Beacon oldBestProximity);

}
