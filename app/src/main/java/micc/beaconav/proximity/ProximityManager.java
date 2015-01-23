package micc.beaconav.proximity;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by nagash on 23/01/15.
 */
public class ProximityManager
{
    private static boolean isAnalyzing = false;

    private ProximityObject lastProximityObject = null;
    private ProximityObject[] proxObjects;

    ProximityNotificationHandler handler;

    ProximityAnalysisTask task = null;


    public ProximityManager(ProximityNotificationHandler handler, ProximityObject[] objects)
    {
        this.handler = handler;
        this.proxObjects = objects;
    }
    public ProximityManager(ProximityNotificationHandler handler)
    {
        this.handler = handler;
        this.proxObjects = null;
    }

    public boolean startProximityAnalysis(double myLat, double myLong, float proximityRadius, float proximitySquare)
    {
        if(task == null && proxObjects != null)
        {
            task = new ProximityAnalysisTask(this, new LatLng(myLat, myLong), proxObjects);
            task.startAnalysis();
            return true;
        }
        else return false;
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
