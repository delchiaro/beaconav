package micc.beaconav.localization.beaconHelper;

import com.estimote.sdk.Beacon;

import java.util.List;

/**
 * Created by nagash on 15/02/15.
 */
public interface BeaconBestProximityListener {

    public void OnNewBeaconBestProximity(Beacon bestProximity, Beacon oldBestProximity);

    public void OnNoneBeaconBestProximity( Beacon oldBestProximity);

}
