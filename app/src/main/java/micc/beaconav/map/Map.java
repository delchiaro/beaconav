package micc.beaconav.map;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import micc.beaconav.R;

/**
 * Created by nagash on 02/12/14.
 */
public class Map
{

    private GoogleMap gmap; // Might be null if Google Play services APK is not available.


    public Map(GoogleMap mMap)
    {
        this.gmap = mMap;
        setUpMap();
    }




    private void setUpMap()
    {
        gmap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

}
