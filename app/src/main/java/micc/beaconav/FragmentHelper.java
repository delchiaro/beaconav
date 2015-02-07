package micc.beaconav;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.View;

import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.gui.backPressedListeners.ToMapOnBackPressedListener;
import micc.beaconav.gui.backPressedListeners.VoidOnBackPressedListener;
import micc.beaconav.gui.backPressedListeners.ToListOnBackPressedListener;
import micc.beaconav.gui.customList.ArtListFragment;
import micc.beaconav.gui.customList.MuseumDescrFragment;
import micc.beaconav.gui.customSlidingHeader.MuseumNameHeaderFragment;
import micc.beaconav.gui.customSlidingHeader.SeekBarHeaderFragment;
import micc.beaconav.indoorEngine.IndoorMapFragment;
import micc.beaconav.outdoorEngine.Map;
import micc.beaconav.outdoorEngine.MapFragment;

/**
 * Created by nagash on 01/02/15.
 */

//A questo punto per ogni Fragment sparato c'è un'imlementazione di OnBackPressedListener che spara il fragment precedente

public class FragmentHelper
{
    private static MainActivity mainActivity = null;
    private static FragmentHelper istance = null;
    private static boolean indoorMode = false;
    private static boolean onMuseumDescrFragment = false;



    public static ArtListFragment artListFragment = new ArtListFragment();
    public static MapFragment mapFragment = new MapFragment();
    public static MuseumDescrFragment museumDescrFragment = new MuseumDescrFragment();
    public static IndoorMapFragment indoorMapFragment = new IndoorMapFragment();
    public static SeekBarHeaderFragment seekBarHeaderFragment = new SeekBarHeaderFragment();
    public static MuseumNameHeaderFragment museumNameHeaderFragment = new MuseumNameHeaderFragment();


    public static void setMainActivity(MainActivity activity) {
        if(FragmentHelper.mainActivity == null)
            FragmentHelper.mainActivity = activity;
        // settabile solo 1 volta
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public static FragmentHelper getIstance(){
        if(istance == null) {
            istance = new FragmentHelper();
        }
        return istance;
    }

    public static boolean isIndoorMode() {
        return indoorMode;
    }

    public static void setIndoorMode(boolean indoorMode) {
        FragmentHelper.indoorMode = indoorMode;
    }

    public static boolean isOnMuseumDescrFragment() {
        return onMuseumDescrFragment;
    }

    public static void setOnMuseumDescrFragment(boolean onMuseumDescrFragment) {
        FragmentHelper.onMuseumDescrFragment = onMuseumDescrFragment;
    }

    //+++++++++++++++++++++++++METODI SETUP PER LA VISUALIZZAIONE DEI FRAGMENT+++++++++++++++++++

    public static final void showListFragment() {
        swapFragment(R.id.fragment_list_container, artListFragment);

    }

    public static final void showMuseumDescrFragment(final MuseumRow row) {
        swapFragment(R.id.fragment_list_container, museumDescrFragment);
        museumDescrFragment.setMuseumRow(row);
        onMuseumDescrFragment = true;
        mainActivity.backPressedListener = new ToListOnBackPressedListener();
    }

    public static  final void showMapFragment() {
        swapFragment(R.id.fragment_map_container, mapFragment);
        mapFragment.setMuseumMarkerManager(mainActivity);
    }

    public static final void showIndoorMapFragment()
    {
        swapFragment(R.id.fragment_map_container, indoorMapFragment);
        //TODO:fare qui lo swap alle liste di opere dentro il museo
        mainActivity.backPressedListener = new ToMapOnBackPressedListener();
        indoorMode = true;
    }

    public static final void showSeekbarHeaderFragment()
    {
        swapFragment(R.id.fragment_sliding_header_container, seekBarHeaderFragment);
        mainActivity.backPressedListener = new VoidOnBackPressedListener();
    }

    public static final void showMuseumNameHeaderFragment(final MuseumRow row)
    {
        swapFragment(R.id.fragment_sliding_header_container, museumNameHeaderFragment);
        museumNameHeaderFragment.setMuseumRow(row);
        mainActivity.backPressedListener = new ToListOnBackPressedListener();
    }


    //+++++++++++++++++++++++++METODI BEHAVIORAL+++++++++++++++++++++++++++++++//

    public static final void simulateMuseumOnMapClick(final MuseumRow row){
        Map.getIstance().simulateMuseumClick(row);
        showMuseumNameHeaderFragment(row);
    }

    public static final void navigateToMuseumOnBtnClick(final MuseumRow row, View v)
    {
        Map.getIstance().simulateMuseumClick(row);
        mapFragment.onClickNavigate(v);
    }



    //Metodo per lo swap di fragments
    private static final void swapFragment(int containerID, Fragment newFragment)
    {
        FragmentManager fragmentManager = mainActivity.getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(containerID, newFragment);
        //transaction.addToBackStack(null);
        transaction.commit();

    }



}
