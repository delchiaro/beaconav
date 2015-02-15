package micc.beaconav.localization.beaconHelper;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.Utils;
import com.google.common.collect.ForwardingSortedSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import micc.beaconav.indoorEngine.beaconHelper.*;
import micc.beaconav.indoorEngine.beaconHelper.BeaconHelper;

/**
 * Created by nagash on 15/02/15.
 */
public class BeaconProximityManager implements BeaconProximityListener {

    private final int DEFAULT_CANDIDATE_TRESHOLD = 900;



    int _candidateTreshold = DEFAULT_CANDIDATE_TRESHOLD;

    Beacon _bestBeaconCandidate = null;
    int _candidate_goodPoints = 0;
    int _candidate_badPoints  = 0;

    Beacon _bestBeacon = null;
    int _oldBest_goodPoints = 0;
    int _oldBest_badPoints  = 0;

    public BeaconProximityManager () {
    }


    @Override
    public void OnBeaconProximity(List<Beacon> proximityBeacons) {

        ArrayList<Beacon> inNearProximityBeacons = new ArrayList<>(proximityBeacons.size());

        for(Beacon beacon : proximityBeacons)
        {
            if(isInProximity(beacon))
            {
                inNearProximityBeacons.add(beacon);
            }
        }




    }




    private static Beacon getNearestBeacon(List<Beacon> beacons) {

        TreeMap<Double, Beacon> beaconsSortedMap = new TreeMap<>();

        for(Beacon b : beacons)
        {
            beaconsSortedMap.put(getDistance(b), b );
        } // ordino in un TreeMap i beacon per distanza

        Iterator<Beacon> beaconIter = beaconsSortedMap.values().iterator();
        return beaconIter.next(); // ritorno il primo (più vicino, distanza minore)
    }


    private static double getDistance(Beacon beacon) {
        return Utils.computeAccuracy(beacon);
    }

    private static boolean isInProximity(Beacon beacon)
    {
        if(Utils.computeProximity(beacon) != Utils.Proximity.NEAR) // NEAR <= 0.5m
            return false;
        else return true;
    }
}
