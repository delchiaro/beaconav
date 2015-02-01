package micc.beaconav;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.gui.customList.ArtListFragment;
import micc.beaconav.gui.customList.MuseumDescrFragment;
import micc.beaconav.outdoorEngine.MapFragment;

/**
 * Created by nagash on 01/02/15.
 */
public class FragmentHelper
{
    private static MainActivity mainActivity = null;
    private static FragmentHelper istance = null;



    public static ArtListFragment artListFragment = new ArtListFragment();
    public static MapFragment mapFragment = new MapFragment();
    public static MuseumDescrFragment museumDescrFragment = new MuseumDescrFragment();


    public static void setMainActivity(MainActivity activity) {
        if(FragmentHelper.mainActivity == null)
            FragmentHelper.mainActivity = activity;
        // settabile solo 1 volta
    }

    public static FragmentHelper getIstance(){
        if(istance == null) {
            istance = new FragmentHelper();
        }
        return istance;
    }


    public static final void showMuseumDescrFragment(final MuseumRow row) {
        swapFragment(R.id.fragment_list_container, museumDescrFragment);
        museumDescrFragment.setMuseumRow(row);
    }

    public static final void showListFragment() {
        swapFragment(R.id.fragment_list_container, artListFragment);


    }

    public static  final void showMapFragment() {
        swapFragment(R.id.fragment_map_container, mapFragment);
        mapFragment.setMuseumMarkerManager(mainActivity);
    }



    //Metodo per lo swap di fragments
    private static final void swapFragment(int containerID, Fragment newFragment)
    {
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerID, newFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }



}
