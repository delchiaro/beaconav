package micc.beaconav.localization.outdoorProximity;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

import java.util.LinkedList;

/**
 * Created by nagash on 23/01/15.
 */
public class ProximityManager
{
    private static boolean isAnalyzing = false;

    private ProximityObject lastProximityObject = null;
    private ProximityObject[] proxObjects;

    private int proximityRadius;

    private LinkedList<ProximityObject> skimmedProxyObjects = null;
    private Location skimmingLocation = null;
    private int skimmingRadius;


    ProximityNotificationHandler handler;

    ProximityAnalysisTask task = null;


    public ProximityManager(int proximityRadius, int skimmingRadius, ProximityNotificationHandler handler, ProximityObject[] objects)
    {
        this.handler = handler;
        this.proxObjects = objects;
        this.proximityRadius = proximityRadius;
        this.skimmingRadius = skimmingRadius;
    }
    public ProximityManager(int proximityRadius, int skimmingRadius,ProximityNotificationHandler handler)
    {
        this(proximityRadius, skimmingRadius, handler, null);
    }

    public boolean startProximityAnalysis(double myLat, double myLong, float proximityRadius, float skimmingRadius)
    {
        Location myLoc = new Location("myLocation");
        myLoc.setLatitude(myLat);
        myLoc.setLongitude(myLong);

        if(myLoc.distanceTo(skimmingLocation) > skimmingRadius - proximityRadius)
        {
            makeNewSkimming(myLoc);
        }


        if(task == null && skimmedProxyObjects != null && skimmedProxyObjects.size() > 0)
        {
            task = new ProximityAnalysisTask(this, new LatLng(myLat, myLong),
                    skimmedProxyObjects.toArray(new ProximityObject[skimmedProxyObjects.size()]));
            task.startAnalysis();
            return true;
        }
        
        else return false;
    }

    private void makeNewSkimming(Location newSkimmingLoc)
    {
        this.skimmedProxyObjects = new LinkedList<>();
        if(proxObjects != null && proxObjects.length > 0)
        {
            Location loc = new Location("testLoc");
            for(ProximityObject po : proxObjects)
            {
                loc.setLatitude(po.getLatitude());
                loc.setLongitude(po.getLongitude());

                if (newSkimmingLoc.distanceTo(loc) < this.skimmingRadius)
                {
                    skimmedProxyObjects.add(po);
                }
            }
        }
    }


    public void setProximityObjects(ProximityObject[] objects)
    {
        this.proxObjects = objects;
    }

    public ProximityObject getLastProximityObject()
    {
        return lastProximityObject;
    }


    void onProximityAnalysisExecuted(ProximityObject object)
    {
        this.lastProximityObject = object;
        this.handler.handleProximityNotification(object);
        this.task = null;
    }

}
