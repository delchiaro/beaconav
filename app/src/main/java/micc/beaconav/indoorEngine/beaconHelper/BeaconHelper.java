package micc.beaconav.indoorEngine.beaconHelper;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

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

public class BeaconHelper
{
    private static final int wait_time_between_scan = 1000;
    private static final String ESTIMOTE_PROXIMITY_UUID = "B9407F30-F5F8-466E-AFF9-25556B57FE6D";
    private static final Region ALL_ESTIMOTE_BEACONS = new Region("rid", null, null, null);


    private BeaconHelper instance = null;
    private final Context context;
    private final Activity activity;
    private BeaconManager  beaconManager = null;


    private List<BeaconProximityListener> proximityListeners = new ArrayList<>();
    public List<Beacon> foundBeacons;


    private static final int REQUEST_ENABLE_BT = 1234;

    public BeaconHelper(Activity activity) {
        this.context = activity;
        this.activity = activity;
        beaconManager = new BeaconManager(activity);





        final Activity myActivity = activity;


        beaconManager.setRangingListener(new BeaconManager.RangingListener()
        {

            @Override
            public void onBeaconsDiscovered(Region region, final List<Beacon> beacons) {
                myActivity.runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        foundBeacons = beacons;
                        if (beacons != null && beacons.size() > 0)
                        {
                            int nListeners = proximityListeners.size();
                            for (int i = 0; i < nListeners; i++)
                            {
                                proximityListeners.get(i).OnBeaconProximity(beacons);
                            }
                        }
                    }
                });

            }
        });



    }







    public void addProximityListener(BeaconProximityListener listener) {
        this.proximityListeners.add(listener);
    }

    public void scanBeacons()
    {


        if(! beaconManager.hasBluetooth()) {
            Toast.makeText(context, "Device does not have bluetooth low energy", Toast.LENGTH_LONG).show();
        }
        if(!beaconManager.isBluetoothEnabled())
        {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            activity.startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        else
        {
            connect();
        }


    }

    private void connect()
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

    public void stopScan() {
        beaconManager.disconnect();
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
