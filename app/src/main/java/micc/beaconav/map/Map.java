package micc.beaconav.map;

import android.location.Location;
import android.os.AsyncTask;
import android.graphics.Color;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.dbHelper.MuseumRow;
import micc.beaconav.dbHelper.MuseumSchemaFactory;
import micc.beaconav.dbJSONManager.JSONHandler;
import micc.beaconav.dbJSONManager.JSONLoader;
import micc.beaconav.dbJSONManager.schema.TableRow;
import micc.beaconav.dbJSONManager.schema.TableSchema;
import micc.beaconav.map.navigation.GMapRouteManager;
import micc.beaconav.map.navigation.Navigation;
import micc.beaconav.proximity.ProximityManager;
import micc.beaconav.proximity.ProximityNotificationHandler;
import micc.beaconav.proximity.ProximityObject;


/**
 * Created by nagash on 02/12/14.
 */
public class Map implements JSONHandler, ProximityNotificationHandler
{



    private GoogleMap gmap; // Might be null if Google Play services APK is not available.
    private LatLng destMarkerLatLng;
    private LatLng customMarkerLatLng;

    private Marker destMarker;
    private Marker customMarker;

    private ArrayList<MuseumRow> rows = null;
    private ArrayList<Marker>    museumMarkers = null;
    private final String jsonMuseumListURL = "http://whitelight.altervista.org/JSONTest.php";

    private boolean polyline = false;

    private LatLng lastLocation = null;

    private Circle circle;
    private CircleOptions circleOptions;


    private ProximityManager proximityManager;
    private boolean fakeProximity = false;
    private ProximityObject lastProxyMuseum = null;




    public Map(GoogleMap mMap)
    {
        this.gmap = mMap;
        this.gmap.setMyLocationEnabled(true);
        destMarkerLatLng = null;
        customMarkerLatLng = null;
        destMarker = null;
        customMarker = null;

        proximityManager = new ProximityManager(this);


        setUpDbObjects();

        setUpEvents();

        LatLng coord = new LatLng(43.8007117, 11.2435291);
        // Instantiates a new CircleOptions object and defines the center and radius
        circleOptions = new CircleOptions()
                .center(new LatLng(37.4, -122.1))
                .radius(1000)// In meters
                .strokeColor(Color.parseColor("#FF9800"))
                .strokeWidth(5)
                .fillColor(Color.parseColor("#20FFA726"));
        // Get back the mutable Circle
        circle = gmap.addCircle(circleOptions);
    }

    private void setUpDbObjects()
    {
        MuseumSchemaFactory factory = new MuseumSchemaFactory();
        TableSchema schema = factory.getSchema();
        JSONLoader jsonLoader = new JSONLoader(schema, this);
        jsonLoader.startDownload(jsonMuseumListURL);
    }

    @Override
    public void onJSONDownloadFinished(ArrayList<TableRow> result)
    {
        this.rows = new ArrayList<>();

        Iterator<TableRow> iter = result.iterator();

        while(iter.hasNext())
        {
            MuseumRow row = new MuseumRow( iter.next() );
            this.rows.add(row);
        }
        drawMarkers();

        proximityManager.setProximityObjects(rows.toArray(new ProximityObject[rows.size()]));
    }



    public void zoomOnLatLng(LatLng latLng, float zoom)
    {
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
    @Override
    public void handleProximityNotification(ProximityObject object)
    {
        if(this.lastProxyMuseum == null || this.lastProxyMuseum != null && this.lastProxyMuseum != object)
        {
            zoomOnLatLng(new LatLng(object.getLatitude(), object.getLongitude()), 16);
            this.lastProxyMuseum = object;
        }
        else lastProxyMuseum = object;
    }
    public void setFakeProximity(boolean val){
        this.fakeProximity = val;
    }
    public boolean getFakeProximity(){
        return this.fakeProximity;
    }
    public void resetLastProxyMuseum(){
        this.lastProxyMuseum = null;
    }






    private void setUpEvents()
    {
        gmap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point)
            {
                if(getCustomMarkerLatLng() == null)
                    setCustomMarkerLatLng(point);
                else
                    unsetCustomLocationMarker();
            }
        });
        gmap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                setDestMarkerLatLng(point);
            }
        });

        gmap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
            @Override
            public void onMyLocationChange(Location location) {
                lastLocation = new LatLng( location.getLatitude(), location.getLongitude());
                circle.setCenter(lastLocation);
                proximityManager.startProximityAnalysis(location.getLatitude(), location.getLatitude(), 10, 500);

            }
        });
    }






    public void setDestMarkerLatLng(LatLng point) {
        destMarkerLatLng = point;
        drawMarkers();
    }
    public LatLng getDestMarkerLatLng(){
        if(this.destMarkerLatLng != null)
            return new LatLng(destMarkerLatLng.latitude, destMarkerLatLng.longitude);
        else return null;
    }

    public void setCustomMarkerLatLng(LatLng currentLocPoint) {
        customMarkerLatLng = currentLocPoint;
        drawMarkers();
    }
    public void unsetCustomLocationMarker() {
        customMarkerLatLng = null;
        drawMarkers();
    }
    public LatLng getCustomMarkerLatLng() {
        if(this.customMarkerLatLng != null)
            return new LatLng(customMarkerLatLng.latitude, customMarkerLatLng.longitude);
        else return null;
    }

    public Location getCurrentLocation()
    {
        return this.gmap.getMyLocation();
    }







    protected void drawMarkers()
    {
        if(this.polyline)
        {
            initMapDrawing();
        }

        if(destMarkerLatLng != null)
        {
            if(this.destMarker != null) destMarker.remove();

            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(this.destMarkerLatLng);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            this.destMarker = gmap.addMarker(markerOptions);
        }
        if(customMarkerLatLng !=null)
        {
            if(this.customMarker != null) customMarker.remove();

            MarkerOptions currentLocationOptions = new MarkerOptions();
            currentLocationOptions.position(this.customMarkerLatLng);
            currentLocationOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
            this.customMarker = gmap.addMarker(currentLocationOptions);
        }


        if(this.rows != null)
        {
            if(this.museumMarkers != null )
            {
                Iterator<Marker> iter = this.museumMarkers.iterator();
                while(iter.hasNext())
                {
                    iter.next().remove();;
                }
            }

            this.museumMarkers = new ArrayList<>();

            Iterator<MuseumRow> iter = this.rows.iterator();
            while(iter.hasNext())
            {
                MuseumRow row = iter.next();
                MarkerOptions museumMarkOpt = new MarkerOptions();
                museumMarkOpt.position(new LatLng(row.getLatitude(), row.getLongitude()));
                museumMarkOpt.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                this.museumMarkers.add( gmap.addMarker(museumMarkOpt));
            }
        }
    }

    protected void initMapDrawing()
    {
        gmap.clear();
        this.polyline = false;
        if(lastLocation != null)
            circleOptions.center(lastLocation);

        circle = gmap.addCircle(circleOptions);

    }


    public Map setRadius(int radius)
    {
        circle.setRadius(radius);
        return this;
    }







    public void route()
    {
        LatLng myLocation = new LatLng(this.gmap.getMyLocation().getLatitude(), this.gmap.getMyLocation().getLongitude());
        route(myLocation, this.destMarkerLatLng);
    }
    public void routeFromCustomMarker()
    {
        route(this.customMarkerLatLng, this.destMarkerLatLng);
    }

    public void route(LatLng startLocation)
    {
        route(startLocation, this.destMarkerLatLng);
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

        this.polyline = true;
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
