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

    private View myFragmentView;


    public MapFragment(){
        super();
    }


    private MuseumMarkerManager manager;

    private Button buttonIndoor;
    private Button buttonNavigate;
    private Button buttonProximity;
    private Button buttonJson;
    private Button buttonLocation;
    private Button buttonSingleLocation;

// * * * * SET UP FRAGMENT * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *


    public void setMuseumMarkerManager(MuseumMarkerManager manager)
    {
        this.manager = manager;
    }

    private void setUp()
    {

        buttonIndoor         =  (Button) myFragmentView.findViewById(R.id.buttonIndoor);
        buttonNavigate       =  (Button) myFragmentView.findViewById(R.id.buttonNavigate);
        buttonProximity      =  (Button) myFragmentView.findViewById(R.id.buttonProximity);
        buttonJson           =  (Button) myFragmentView.findViewById(R.id.buttonJson);
        buttonLocation       =  (Button) myFragmentView.findViewById(R.id.buttonLocation);
        buttonSingleLocation =  (Button) myFragmentView.findViewById(R.id.buttonSingleLocation);

    }


    private GoogleMap getGMapFromXML()
    {
        // Try to obtain the map from the SupportMapFragment.
        return ((SupportMapFragment)((FragmentActivity)getActivity()).getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        //return ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
    }







// * * * * SET UP EVENT LISTENER * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    public void setUpEventListeners()
    {

        buttonIndoor.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v) { onClickButtonIndoor(v); }
        });

        buttonNavigate.setOnClickListener(new View.OnClickListener()
        {
            @Override  public void onClick(View v) { onClickNavigate(v); }
        });

        buttonJson.setOnClickListener(new View.OnClickListener()
        {
            @Override  public void onClick(View v) {
                Intent intent = new Intent(context, JSONTest.class);
                startActivity(intent);
            }
        });

        buttonLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override  public void onClick(View v) {
                Intent intent = new Intent(context, testAdaptedLocationActivity.class);
                startActivity(intent);
            }
        });
        buttonSingleLocation.setOnClickListener(new View.OnClickListener()
        {
            @Override  public void onClick(View v) {
                Intent intent = new Intent(context, testLastLocationActivity.class);
                startActivity(intent);
            }
        });

        buttonProximity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { setFakeProximity(v); }
        });
    }

    public void onClickNavigate(View view)
    {
        map.route();
    }


    public void setFakeProximity(View view)
    {
        map.resetLastProxyMuseum();
        if(map.getFakeProximity() == false) {
            map.setFakeProximity(true);
        }

    }

    public void onClickButtonIndoor(View view)
    {
        //Intent intent = new Intent(this, micc.beaconav.multitouch.TouchActivity.class);
        Intent intent = new Intent(this.context, micc.beaconav.newTouchActivity.class);
        startActivity(intent);
    }














// * * * * OVERRIDE METHODS * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        myFragmentView = inflater.inflate(R.layout.fragment_map, container, false);
        context = this.getActivity();
        setUp();
        setUpEventListeners();
        map = new Map(getGMapFromXML(), manager);
        return myFragmentView;
       //Button buttonIndoor = (Button) getView().findViewById(R.id.btnIndoor);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getArguments();
    }








}
