package micc.beaconav.map;

import android.location.Location;
import android.os.AsyncTask;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import micc.beaconav.R;
import micc.beaconav.map.navigation.GMapRouteManager;
import micc.beaconav.map.navigation.Navigation;


/**
 * Created by nagash on 02/12/14.
 */
public class Map
{
    private GoogleMap gmap; // Might be null if Google Play services APK is not available.
    private LatLng destinationMarker;
    private LatLng customLocationMarker;
    Circle circle;
    CircleOptions circleOptions;

    public Map(GoogleMap mMap)
    {
        this.gmap = mMap;
        this.gmap.setMyLocationEnabled(true);
        destinationMarker = null;
        customLocationMarker = null;
        setUpEvents();

        LatLng coord = new LatLng(43.8007117, 11.2435291);
        // Instantiates a new CircleOptions object and defines the center and radius
        circleOptions = new CircleOptions()
                .center(new LatLng(37.4, -122.1))
                .radius(1000)// In meters
                .fillColor(Color.RED);

        // Get back the mutable Circle
        circle = gmap.addCircle(circleOptions);



    }

    private void setUpEvents()
    {
        gmap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point)
            {
                if(getCustomLocationMarker() == null)
                    setCustomLocationMarker(point);
                else
                    unsetCustomLocationMarker();
            }
        });
        gmap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                setDestinationMarker(point);
            }
        });

        gmap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {

                circleOptions.center(new LatLng(location.getLatitude(), location.getLongitude()));
                Circle circle2 = gmap.addCircle(circleOptions);
                circle.remove();
                circle = circle2;
            }
        });
    }


    public void setDestinationMarker(LatLng point) {
        destinationMarker = point;
        drawMarkers();
    }
    public LatLng getDestinationMarker(){
        if(this.destinationMarker != null)
            return new LatLng(destinationMarker.latitude, destinationMarker.longitude);
        else return null;
    }

     public void setCustomLocationMarker(LatLng currentLocPoint) {
        customLocationMarker = currentLocPoint;
        drawMarkers();
    }
    public void unsetCustomLocationMarker() {
        customLocationMarker = null;
        drawMarkers();
    }
    public LatLng getCustomLocationMarker() {
        if(this.customLocationMarker != null)
            return new LatLng(customLocationMarker.latitude, customLocationMarker.longitude);
        else return null;
    }

    public Location getCurrentLocation()
    {
        return this.gmap.getMyLocation();
    }



    protected void drawMarkers(){

        gmap.clear();


        if(destinationMarker != null)
        {
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(this.destinationMarker);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            gmap.addMarker(markerOptions);
        }
        if(customLocationMarker !=null)
        {
            MarkerOptions currentLocationOptions = new MarkerOptions();
            currentLocationOptions.position(this.customLocationMarker);
            currentLocationOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            gmap.addMarker(currentLocationOptions);
        }
    }









    public void route()
    {
        LatLng myLocation = new LatLng(this.gmap.getMyLocation().getLatitude(), this.gmap.getMyLocation().getLongitude());
        route(myLocation, this.destinationMarker);
    }
    public void routeFromCustomLocation()
    {
        route(this.customLocationMarker, this.destinationMarker);
    }

    public void route(LatLng startLocation)
    {
        route(startLocation, this.destinationMarker);
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
