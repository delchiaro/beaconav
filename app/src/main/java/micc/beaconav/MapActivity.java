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

        LatLng A = new LatLng(map.currentLocation.latitude, map.currentLocation.longitude);
        LatLng B = new LatLng(map.marker.latitude, map.marker.longitude);



        RoutingTask myTask = new RoutingTask();
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB )
            myTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, A, B);
        else
            myTask.execute(A, B);

        for(int i = 0; i < 500; i++ ) Log.d("DEBUG!", "Ciao CIAO CIAO CIAO STO SCRIVENDO SUL LOG!!!!!!!");

    }




    private class RoutingTask extends AsyncTask<LatLng, Void, Navigation>
    {

        protected Navigation doInBackground(LatLng ... pt)
        {
            Navigation nav = map.route(pt[0] , pt[1]);
            return nav;
        }

        protected void onPostExecute(Navigation nav)
        {
            map.drawNavigation(nav);
        }

    }


}
