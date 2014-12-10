package micc.beaconav.map;

import android.os.AsyncTask;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import micc.beaconav.map.navigation.GMapRouteManager;
import micc.beaconav.map.navigation.Navigation;


/**
 * Created by nagash on 02/12/14.
 */
public class Map
{
    private GoogleMap gmap; // Might be null if Google Play services APK is not available.
    private LatLng markerLocation;
    private LatLng currentLocation;

    public Map(GoogleMap mMap)
    {
        this.gmap = mMap;
        setUpEvents();
    }

    private void setUpEvents()
    {
        gmap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point)
            {
                setMarkerLocation(point);

            }
        });
        gmap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                setCurrentLocation(point);
            }
        });
    }



    public LatLng getMarkerLocation()
    {
        return new LatLng(markerLocation.latitude, markerLocation.longitude);
    }
    public void setMarkerLocation(LatLng point)
    {
        markerLocation = point;
        drawMarkers();
    }


    public LatLng getCurrentLocation()
    {
        return new LatLng(currentLocation.latitude, currentLocation.longitude);
    }

    public void setCurrentLocation(LatLng currentLocPoint)
    {
        currentLocation = currentLocPoint;
        drawMarkers();
    }


    protected void drawMarkers()
    {
        gmap.clear();
        if(markerLocation != null)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(this.markerLocation);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            gmap.addMarker(markerOptions);
        }
        if(currentLocation!=null)
        {
            MarkerOptions currentLocationOptions = new MarkerOptions();
            currentLocationOptions.position(this.currentLocation);
            currentLocationOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            gmap.addMarker(currentLocationOptions);
        }
    }


    public void route()
    {
        route(this.currentLocation, this.markerLocation);
    }

    public void route(LatLng startLocation)
    {
        route(startLocation, this.markerLocation);
    }

    public void route(LatLng origin, LatLng dest)
    {
        if(origin != null && dest != null) {
            RouteTask task = new RouteTask();
            task.execute(origin, dest);

            /********************** IF MULTITASKING DOES NOT WORK TRY THIS: ******************************************

             if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
             task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, origin, dest);
             else
             task.execute(origin, dest);

             ***************************************************************************************************/

            Log.d("DEBUG", "Downloading directions from google servers...");
        }
    }


    private Navigation _route(LatLng origin, LatLng dest)
    {
        GMapRouteManager routeManager = new GMapRouteManager();
        return routeManager.requestRoute(origin, dest);
    }

    private void _drawNavigation(Navigation nav)
    {
        //disegnare la navigazione sulla mappa
        PolylineOptions lineOptions = new PolylineOptions();
        lineOptions.width(10);
        lineOptions.color(Color.RED);

        nav.draw(gmap, lineOptions, 0);
        // TODO: aggiungere schermata con caricamento e/o disegnare la polyline usando solo punti "abbastanza lontani" calcolando la distanza euclidea e confrontandola col livello di zoom..

    }



    private class RouteTask extends AsyncTask<LatLng, Void, Navigation>
    {

        protected Navigation doInBackground(LatLng ... pt)
        {
            Navigation nav = _route(pt[0], pt[1]);
            return nav;
        }

        protected void onPostExecute(Navigation nav)
        {
            _drawNavigation(nav);
        }

    }


}
