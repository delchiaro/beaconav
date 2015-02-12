package micc.beaconav.indoorEngine.beaconHelper;

import android.content.Context;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.Utils;

import java.util.ArrayList;
import java.util.List;

import micc.beaconav.FragmentHelper;

/**
 * Created by Mr_Holmes on 11/02/15.
 */

public class BeaconHelper extends AsyncTask<String, String, Void>
{
    private static final int wait_time_between_scan = 1000;
    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", ESTIMOTE_PROXIMITY_UUID, null, null);


    private static BeaconHelper instance = null;
    private static Context context = null;
    private static BeaconManager  beaconManager = null;


    public static boolean isInstanziated() {
        return (instance != null );
    }

    public static BeaconHelper instance() {
        if(instance == null) instance = new BeaconHelper();
        return instance;
    }
    public static void init(Context context) {
        if(BeaconHelper.context == null)
            BeaconHelper.context = context;
    }




    private BeaconHelper() {
        beaconManager = new BeaconManager(context);
    }



    private boolean executionStarted = false;

    public void startScan(){
        if(executionStarted == true) {
            stopScan();
            instance.startScan();
        }
        else {
            executionStarted = true;
            execute();
        }
    }
    public void stopScan(){
        Context context = instance.context;
        instance = new BeaconHelper();
        init(context);

    }


    private static List<BeaconProximityListener> proximityListeners = new ArrayList<>();
    public static List<Beacon> foundBeacons;




    public void addProximityListener(BeaconProximityListener listener) {
        this.proximityListeners.add(listener);
    }

    public List<Beacon> scanBeacons() {

        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> beacons) {
                foundBeacons = beacons;
            }
        });

        if(foundBeacons!= null)
        while(foundBeacons.isEmpty())
        {
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
        return foundBeacons;
    }





    @Override
    protected Void doInBackground(String... params) {
        try {
            Thread.sleep(wait_time_between_scan);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<Beacon> scannedBeacons = scanBeacons();

        if(scannedBeacons != null && scannedBeacons.size() > 0 )
        {
            int nListeners = proximityListeners.size();
            for(int i = 0; i < nListeners; i++ )
            {
                proximityListeners.get(i).OnBeaconProximity(scannedBeacons);
            }
        }

        return null;
    }





    public static double getDistance(Beacon beacon) {
        return Utils.computeAccuracy(beacon);
    }

    public static boolean isInProximity(Beacon beacon) {
        if(Utils.computeProximity(beacon) != Utils.Proximity.NEAR) // NEAR <= 0.5m
        {
            return false;
        }
        else return true;
    }

}
