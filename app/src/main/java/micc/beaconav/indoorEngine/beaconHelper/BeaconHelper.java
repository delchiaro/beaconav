package micc.beaconav.indoorEngine.beaconHelper;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.List;

import micc.beaconav.FragmentHelper;

/**
 * Created by Mr_Holmes on 11/02/15.
 */

public class BeaconHelper {

    private Beacon beacon;
    private Region region;
    private static Context context;
    private List<Beacon> beacons;
    private static BeaconHelper instance = null;
    public static List<Beacon> foundBeacons;
    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);

//    public BeaconHelper(Beacon beacon, Region region, Context context) {
//        this.beacon = beacon;
//        this.region = region;
//        this.context = context;
//    }

    public static BeaconHelper instance()
    {
        if(instance == null) instance = new BeaconHelper();
        return instance;
    }

    public List<Beacon> scanBeacons()
    {
        final BeaconManager beaconManager = new BeaconManager(context);
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
                BeaconHelper.instance().foundBeacons = beacons;
            }
        });

        while(BeaconHelper.instance().foundBeacons.isEmpty()) {
            beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
                @Override
                public void onServiceReady() {
                    try {
                        beaconManager.startRanging(ALL_ESTIMOTE_BEACONS);
                    } catch (RemoteException e) {
                        Log.e("TAG", "Cannot start ranging", e);
                    }
                }
            });
        }


        beaconManager.disconnect();

        return BeaconHelper.instance().foundBeacons;
    }


    public double getDistance(Beacon beacon)
    {
        return Utils.computeAccuracy(beacon);
    }

    public boolean isInProximity(Beacon beacon)
    {
        if(Utils.computeProximity(beacon) != Utils.Proximity.NEAR) // NEAR <= 0.5m
        {
            return false;
        }
        else return true;
    }



}
