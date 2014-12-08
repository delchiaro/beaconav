package micc.beaconav;

import micc.beaconav.map.Map;
import micc.beaconav.map.navigation.GMapRouteManager;
import micc.beaconav.map.navigation.Navigation;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.os.Build;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;


public class MapActivity extends FragmentActivity {



    private Map map; // Might be null if Google Play services APK is not available.


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        map = new Map( this.getGMapFromXML() );
    }


    private GoogleMap getGMapFromXML()
    {
        // Try to obtain the map from the SupportMapFragment.
        return ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }





    @Override
    protected void onResume()
    {
        super.onResume();
    }



    public void eventManager()
    {

    }

    public void onClickNavigate(View view)
    {
        map.route();
    }



}
