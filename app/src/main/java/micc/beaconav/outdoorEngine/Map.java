package micc.beaconav.outdoorEngine;

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
import java.util.HashMap;
import java.util.Iterator;

import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.MuseumRow;
import micc.beaconav.db.dbHelper.MuseumSchemaFactory;
import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.db.dbJSONManager.JSONDownloader;
import micc.beaconav.db.dbJSONManager.schema.TableRow;
import micc.beaconav.db.dbJSONManager.schema.TableSchema;
import micc.beaconav.outdoorEngine.navigation.GMapRouteManager;
import micc.beaconav.outdoorEngine.navigation.Navigation;
import micc.beaconav.localization.proximity.ProximityManager;
import micc.beaconav.localization.proximity.ProximityNotificationHandler;
import micc.beaconav.localization.proximity.ProximityObject;


/**
 * Created by nagash on 02/12/14.
 */
public class Map implements JSONHandler, ProximityNotificationHandler
{



    private GoogleMap gmap; // Might be null if Google Play services APK is not available.


    private Marker selectedMuseumMarker = null;
    MarkerOptions museumMarkerOptions = new MarkerOptions();
    MarkerOptions selectedMuseumMarkerOptions = new MarkerOptions();


    private ArrayList<MuseumRow> rows = null;
    private HashMap<Marker, MuseumRow> museumMarkersMap = null;
    private MuseumMarkerManager markerManager = null;


    private boolean polyline = false;

    private LatLng lastLocation = null;

    private Circle circle;
    private CircleOptions circleOptions;


    private ProximityManager proximityManager;
    private boolean fakeProximity = false;
    private ProximityObject lastProxyMuseum = null;




    public Map(GoogleMap mMap, MuseumMarkerManager markerManager)
    {
        this.gmap = mMap;
        this.gmap.setMyLocationEnabled(true);

        proximityManager = new ProximityManager(this);

        this.markerManager = markerManager;

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


        museumMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
        selectedMuseumMarkerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));

    }

    private void setUpDbObjects()
    {
        DbManager.museumDownloader.addHandler(this);
        DbManager.museumDownloader.startDownload();
    }

    @Override
    public void onJSONDownloadFinished(TableRow[] result)
    {
        this.rows = new ArrayList<>();

        int resultLenght = result.length;
        for(int i = 0; i < resultLenght; i++)
        {
            MuseumRow row = new MuseumRow( result[i] );
            this.rows.add(row);
        }
        drawMarkers();

        proximityManager.setProximityObjects(rows.toArray(new ProximityObject[rows.size()]));
    }



    public void zoomOnLatLng(LatLng latLng, float zoom)
    {
        gmap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));
    }
    public void goOnLatLng(LatLng latLng)
    {
        gmap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
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

        gmap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
            @Override
            public boolean onMarkerClick(Marker marker) {
                MuseumRow row = museumMarkersMap.get(marker);
                goOnLatLng(marker.getPosition());
                markerManager.onClickMuseumMarker(row);
                setSelectedMuseumMarker(marker);

                return true;
            }
        } );

        gmap.setOnMapLongClickListener(new OnMapLongClickListener() {

            @Override
            public void onMapLongClick(LatLng point)
            {
                unsetMuseumMarker();
            }
        });
        gmap.setOnMapClickListener(new OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                unsetMuseumMarker();
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




    private final void setSelectedMuseumMarker(Marker selectedMarker)
    {
        this.selectedMuseumMarker = selectedMarker;
        drawMarkers();
    }

    private final void unsetMuseumMarker()
    {
        if(selectedMuseumMarker != null)
        {
            selectedMuseumMarker = null;
            drawMarkers();
        }
        markerManager.onDeselectMuseumMarker();
    }

    public Marker getSelectedMuseumMarker(){
        return selectedMuseumMarker;
    }

    public LatLng getSelectedMuseumLatLng(){
        if(this.selectedMuseumMarker != null)
            return selectedMuseumMarker.getPosition();
        else return null;
    }



    public final Location getCurrentLocation()
    {
        return this.gmap.getMyLocation();
    }
    public final LatLng getCurrentLatLng()
    {
        if(this.getCurrentLocation() != null)
            return new LatLng(this.getCurrentLocation().getLatitude(), this.getCurrentLocation().getLongitude());
        else return null;
    }






    protected void drawMarkers()
    {
        if(this.polyline)
        {
            initMapDrawing();
        }



        if(this.rows != null)
        {
            if(this.museumMarkersMap != null )
            {
                Iterator<Marker> iter = this.museumMarkersMap.keySet().iterator();
                while(iter.hasNext())
                {
                    iter.next().remove();;
                }
            }

            this.museumMarkersMap = new HashMap<>();

            Iterator<MuseumRow> iter = this.rows.iterator();
            while(iter.hasNext())
            {
                MuseumRow row = iter.next();
                museumMarkerOptions.position(new LatLng(row.getLatitude(), row.getLongitude()));
                this.museumMarkersMap.put( gmap.addMarker(museumMarkerOptions), row);
                //this.museumMarkers.add( gmap.addMarker(museumMarkOpt));
            }

            if(this.selectedMuseumMarker != null)
            {
                MuseumRow removedRow = museumMarkersMap.get(selectedMuseumMarker);
                museumMarkersMap.remove(selectedMuseumMarker);
                // rimuovo il marker dalla mappa salvandomi il relativo MuseumRow

                selectedMuseumMarkerOptions.position(selectedMuseumMarker.getPosition());
                selectedMuseumMarker.remove();
                // rimuovo il marker da google maps (gmap) salvandomi la posizioni

                selectedMuseumMarker = gmap.addMarker(selectedMuseumMarkerOptions);
                // riinserisco in google map il marker

                museumMarkersMap.put(selectedMuseumMarker, removedRow);
                //riinserisco il marker-museumRow nella mappa
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
        if(getCurrentLatLng() != null && getSelectedMuseumLatLng() != null) {
            route(getCurrentLatLng(), getSelectedMuseumLatLng());
        }
    }

    public void route(LatLng startLocation)
    {
        if(this.selectedMuseumMarker != null)
            route(startLocation, this.selectedMuseumMarker.getPosition());
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
