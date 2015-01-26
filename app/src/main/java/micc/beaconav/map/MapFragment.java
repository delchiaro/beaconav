package micc.beaconav.map;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import micc.beaconav.JSONTest;
import micc.beaconav.R;
import micc.beaconav.testAdaptedLocationActivity;
import micc.beaconav.testLastLocationActivity;

/**
 * Created by nagash on 26/01/15.
 */
public class MapFragment extends Fragment
{


    private Map map; // Might be null if Google Play services APK is not available.
    private Context context;

    public MapFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View myFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        this.context = this.getActivity();
        return myFragmentView;


       //Button buttonIndoor = (Button) getView().findViewById(R.id.btnIndoor);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        map = new Map( this.getGMapFromXML() );

    }




    private GoogleMap getGMapFromXML()
    {
        // Try to obtain the map from the SupportMapFragment.
        return ((SupportMapFragment)((FragmentActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        //return ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }







    private  boolean fakeProximity = false;

    public void setFakeProximity(View view)
    {
        map.resetLastProxyMuseum();
        if(map.getFakeProximity() == false) {
            map.setFakeProximity(true);
        }

    }

    public void onClickNavigate(View view)
    {
        if(map.getCustomMarkerLatLng() != null)
            map.routeFromCustomMarker();
        else map.route();
    }



    public void onClickBtnIndoor(View view)
    {
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this.context, micc.beaconav.newTouchActivity.class);
        startActivity(intent);
    }

    public void onClickBtnTestLocation1(View view)
    {
        //Intent intent = new Intent(this, newTouchActivity.class);
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this.context, testAdaptedLocationActivity.class);
        startActivity(intent);
    }
    public void onClickBtnTestLocation2(View view)
    {
        //Intent intent = new Intent(this, newTouchActivity.class);
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this.context, testLastLocationActivity.class);

        startActivity(intent);
    }



}
