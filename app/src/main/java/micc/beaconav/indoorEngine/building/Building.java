package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

import micc.beaconav.indoorEngine.building.spot.marker.MarkerSpot;
import micc.beaconav.indoorEngine.building.spot.path.PathSpot;
import micc.beaconav.indoorEngine.building.spot.marker.MarkerSpotManager;
import micc.beaconav.indoorEngine.building.spot.path.PathSpotManager;
import micc.beaconav.indoorEngine.dijkstraSolver.DijkstraSolver;
import micc.beaconav.util.containerContained.Container;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
 */
public class Building extends Container<Floor>
{

  //  LatLng p1;
  //  LatLng p2; // Il building è un rettangolo definito tra due punti p1 e p2

	private float width; // in  metri
    private float height; // in metri
    private TreeMap<Integer, Floor> floorList;
    private int _activeFloor;

    DijkstraSolver<PathSpot> dijkstraSolver = new DijkstraSolver<>();
    PathSpotManager<PathSpot> dijkstraPath = null;




    public Building(int width, int height)  {
        this.width = width;
        this.height = height;
        _activeFloor = 0;
        floorList = new TreeMap<Integer, Floor>();
	}



    public float    getWidth(){
        return width;
    }
    public float    getHeight(){
        return height;
    }




    public Floor getActiveFloor() {
        return get(this._activeFloor);
    }


    public MarkerSpotManager getActiveMarkerManager() {
        return this.getActiveFloor().getMarkerManager();
    }
    public PathSpotManager getActivePathSpotManager() {
        return this.getActiveFloor().getPathSpotManager();
    }




    public PathSpotManager<PathSpot> drawBestPath( PathSpot startSpot, PathSpot goalSpot) {
        dijkstraPath = new PathSpotManager( dijkstraSolver.solve(startSpot, goalSpot));

        MarkerSpotManager  markerManager = getActiveMarkerManager();
        if(markerManager != null) {
            dijkstraPath.resetAllTranslationAndScale();
            dijkstraPath.translate(markerManager.get_translation_x(), markerManager.get_translation_y());
            dijkstraPath.translateByRealtimeScaling(markerManager.get_last_final_scaleTranslation_factor());
            dijkstraPath.holdScalingFactor();
            dijkstraPath.translateByRealtimeScaling(markerManager.get_realtime_scaleTranslation_factor());
        }
        return dijkstraPath;
    }







    public void draw(Canvas canvas, int floorIndex) {
        super.get(floorIndex).draw(canvas);
    }
    public void draw(Canvas canvas)
    {
        this.draw(canvas, this._activeFloor);
    }









}