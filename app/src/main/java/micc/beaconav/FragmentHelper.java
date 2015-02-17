package micc.beaconav;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbHelper.artwork.ArtworkRow;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.gui.backPressedListeners.OnBackPressedListener;
import micc.beaconav.gui.customList.ArtListFragment;
import micc.beaconav.gui.customList.ArtworkDescrFragment;
import micc.beaconav.gui.customList.MuseumDescrFragment;
import micc.beaconav.gui.customSlidingHeader.NameHeaderFragment;
import micc.beaconav.gui.customSlidingHeader.SeekBarHeaderFragment;
import micc.beaconav.indoorEngine.IndoorMapFragment;
import micc.beaconav.outdoorEngine.Map;
import micc.beaconav.outdoorEngine.MapFragment;
import micc.beaconav.outdoorEngine.MuseumMarkerManager;

/**
 * Created by nagash on 01/02/15.
 */

//A questo punto per ogni Fragment sparato c'è un'imlementazione di OnBackPressedListener che spara il fragment precedente

public class FragmentHelper  implements MuseumMarkerManager
{

    private static FragmentHelper istance = null;
    private static MainActivity mainActivity = null;

    public static FragmentHelper instance(){
        if(istance == null) istance = new FragmentHelper();
        return istance;
    }

    private FragmentHelper() {

    }

    public static void setMainActivity(MainActivity activity) {
        if(mainActivity == null) {
            mainActivity = activity;
            mainActivity.setOnBackPressedListener(instance().getOnBackPressedListener());
        }
        // settabile solo 1 volta
    }



    public enum SlidingFragment { LIST, DETAILS, NAVIGATE; }
    public enum MainFragment{ OUTDOOR, INDOOR; }



    private MainFragment    activeMainFragment    = MainFragment.OUTDOOR;
    private SlidingFragment activeSlidingFragment = SlidingFragment.LIST;


    public MapFragment       mapFragment = new MapFragment();
    public IndoorMapFragment indoorMapFragment = null;


    public ArtListFragment museumListFragment = new ArtListFragment();
    public ArtListFragment artworkListFragment = new ArtListFragment();
    public MuseumRow artworkList_museumRow = null;

    public MuseumDescrFragment museumDescrFragment = new MuseumDescrFragment();
    public ArtworkDescrFragment artworkDescrFragment = new ArtworkDescrFragment();
    public SeekBarHeaderFragment seekBarHeaderFragment = new SeekBarHeaderFragment();
    public NameHeaderFragment nameHeaderFragment = new NameHeaderFragment();



    public OnBackPressedListener getOnBackPressedListener(){
        return new OnBackPressedListener() {
            @Override
            public void doBack() {
                if(activeMainFragment == MainFragment.OUTDOOR)
                {
                    switch(activeSlidingFragment){
                        case NAVIGATE:
                            MuseumRow selectedMuseumRow = Map.getIstance().getSelectedMuseumRow();
                            simulateDeselectMuseumOnMapClick();
                            simulateMuseumOnMapClick(selectedMuseumRow);
                            break;

                        case DETAILS:
                            simulateDeselectMuseumOnMapClick();
                            showMuseumListFragment();
                            break;

                        case LIST:
                            // void. Nothing to do!
                            break;

                    }

                }
                else if(activeMainFragment == MainFragment.INDOOR)
                {
                    switch(activeSlidingFragment){
                        case NAVIGATE:
                            break;

                        case DETAILS:
                            //showArtworkListFragment(artworkList_museumRow);
                            indoorMapFragment.onMarkerSpotSelected(null);
                            break;

                        case LIST:
                            SlidingUpPanelLayout.PanelState panelState = mainActivity.getSlidingUpPanelLayout().getPanelState();

                            if(panelState == SlidingUpPanelLayout.PanelState.ANCHORED)
                            {

                            }
                            if(mainActivity.getSlidingUpPanelLayout().getPanelState() == SlidingUpPanelLayout.PanelState.COLLAPSED)
                            {
                                AlertDialog alertDialog = new AlertDialog.Builder(mainActivity).create();

                                alertDialog.setTitle("Conferma azione");
                                alertDialog.setMessage("Vuoi uscire dal museo e tornare alla mappa esterna?");

                                // Setting Icon to Dialog
                                //alertDialog.setIcon(R.drawable.);

                                alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        showOutdoorFragment();
                                        indoorMapFragment = null;
                                    }
                                });

                                alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Annulla", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {

                                    }
                                });

                                alertDialog.show();
                            }
                            break;

                    }
                }
            }
        };
    }


    public MainActivity getMainActivity() {
        return mainActivity;
    }


    public boolean isIndoorMode() {
        return activeMainFragment == MainFragment.INDOOR;
    }










    //+++++++++++++++++++++++++METODI SETUP PER LA VISUALIZZAIONE DEI FRAGMENT+++++++++++++++++++


    public final void showOutdoorFragment() {
        indoorMapFragment = null;
        swapFragment(R.id.fragment_map_container, mapFragment);
        activeMainFragment = MainFragment.OUTDOOR;
        mapFragment.setMuseumMarkerManager(this);
        showMuseumListFragment();
        mainActivity.setFABListener(defaultFABOnClickListener);
        mainActivity.getFloatingActionButtonQRScanBtn().setVisibility(View.INVISIBLE);
        mainActivity.getFloatingActionButtonNotifyBeaconProximity().setVisibility(View.INVISIBLE);
        mainActivity.getFloatingActionButtonNotifyToIndoor().setVisibility(View.INVISIBLE);

    }


    public final void showIndoorFragment(MuseumRow museum) {
        indoorMapFragment = new IndoorMapFragment();// gli dovremmo passare il building, o il museo, o il file json del building
        swapFragment(R.id.fragment_map_container, indoorMapFragment);
        activeMainFragment = MainFragment.INDOOR;
        showArtworkListFragment(museum);
        //indoorMapFragment.setMuseum(museum);
        mainActivity.setThemeColor(MainActivity.ThemeColor.RED);
        mainActivity.getFloatingActionButton().setIconDrawable(mainActivity.getResources().getDrawable(R.drawable.white_museum));
        mainActivity.getFloatingActionButtonQRScanBtn().setVisibility(View.VISIBLE);
        mainActivity.getFloatingActionButtonNotifyToIndoor().setVisibility(View.INVISIBLE);


    }






    public final void showArtworkListFragment(MuseumRow museum) {

        if(museum != this.artworkList_museumRow)
        {
            artworkListFragment = new ArtListFragment();
            artworkList_museumRow = museum;
            DbManager.getArtworkDownloader(museum, new JSONHandler<ArtworkRow>() {
                @Override
                public void onJSONDownloadFinished(ArtworkRow[] result) {
                    artworkListFragment.insertRows(result);
                }
            });
        }


        activeSlidingFragment = SlidingFragment.LIST;
        swapFragment(R.id.fragment_list_container, artworkListFragment);
        showNameHeaderFragment(museum);
        mainActivity.setThemeColor(MainActivity.ThemeColor.RED);
        mainActivity.getFloatingActionButton().setIconDrawable(mainActivity.getResources().getDrawable(R.drawable.white_museum));
        mainActivity.setFABListener(defaultFABOnClickListener);
    }

    public final void showMuseumListFragment() {

        if(DbManager.museumDownloader.isDownloadStarted() == false) {
            DbManager.museumDownloader.startDownload();
            DbManager.museumDownloader.addHandler(new JSONHandler<MuseumRow>() {
                @Override
                public void onJSONDownloadFinished(MuseumRow[] result) {
                    museumListFragment.insertRows(result);
                }
            });

        }

        simulateDeselectMuseumOnMapClick();

        activeSlidingFragment = SlidingFragment.LIST;
        swapFragment(R.id.fragment_list_container, museumListFragment);
        showSeekbarHeaderFragment();


        mainActivity.setThemeColor(MainActivity.ThemeColor.ORANGE);
        mainActivity.getFloatingActionButton().setIconDrawable(mainActivity.getResources().getDrawable(R.drawable.white_museum));
        mainActivity.setFABListener(defaultFABOnClickListener);

    }


    public final void showMuseumDetailsFragment(final MuseumRow row) {
        activeSlidingFragment = SlidingFragment.DETAILS;

        showNameHeaderFragment(row);
        swapFragment(R.id.fragment_list_container, museumDescrFragment);
        museumDescrFragment.setMuseumRow(row);
        mainActivity.setThemeColor(MainActivity.ThemeColor.PURPLE);
        mainActivity.getFloatingActionButton().setIconDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_directions_white_48dp));

    }

    public final void showArtworkDetailsFragment(final ArtworkRow row)
    {
        activeSlidingFragment = SlidingFragment.DETAILS;

        showNameHeaderFragment(row);
        artworkDescrFragment = new ArtworkDescrFragment();
        artworkDescrFragment.setArtworkRow(row);
        swapFragment(R.id.fragment_list_container, artworkDescrFragment);
        indoorMapFragment.simulateArtSpotSelection(row);
        mainActivity.setThemeColor(MainActivity.ThemeColor.RED);
        mainActivity.getFloatingActionButton().setIconDrawable(mainActivity.getResources().getDrawable(R.drawable.ic_directions_white_48dp));
//        mainActivity.setFABListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                indoorMapFragment.navigateToSelectedMarker();
//            }
//        });

        //settare il bottone per la navigazione verso l'opera

    }



    private final void showSeekbarHeaderFragment() {
        swapFragment(R.id.fragment_sliding_header_container, seekBarHeaderFragment);
    }
    private final void showNameHeaderFragment(final IArtRow row) {
        swapFragment(R.id.fragment_sliding_header_container, nameHeaderFragment);
        nameHeaderFragment.setArtRow(row);
    }



    //+++++++++++++++++++++++++METODI BEHAVIORAL+++++++++++++++++++++++++++++++//



    @Override
    public void onClickMuseumMarker(MuseumRow museumRow) {
        showMuseumDetailsFragment(museumRow);
    }

    @Override
    public void onDeselectMuseumMarker() {
        showMuseumListFragment();
    }


    public final void simulateDeselectMuseumOnMapClick() {
        Map istance = Map.getIstance();
        if(istance != null)
            istance.simulateUnselectMarker();
    }

    public final void simulateMuseumOnMapClick(final MuseumRow row){
        Map.getIstance().simulateMuseumClick(row);
        // showNameHeaderFragment(row);
    }

    public final void navigateToMuseumOnBtnClick(final MuseumRow row, View v) {
        Map.getIstance().simulateMuseumClick(row);
        mapFragment.onClickNavigate(v);
    }



    private View.OnClickListener defaultFABOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            switch (mainActivity.getSlidingUpPanelLayout().getPanelState())
            {

                case COLLAPSED:
                    mainActivity.getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

                case ANCHORED:
                    mainActivity.getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

                case EXPANDED:
                    mainActivity.getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);

            }
        }

    };


    //  HELPER PER INTERFACCIA GRAFICA
    public void resetInitialSeekBarRadius() {
        seekBarHeaderFragment.resetInitialSeekBarRadius();
    }


    //Metodo per lo swap di fragments
    private final void swapFragment(int containerID, Fragment newFragment) {
        if (containerID != newFragment.getId()) {
            FragmentManager fragmentManager = mainActivity.getFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(containerID, newFragment);
            //transaction.addToBackStack(null);
            transaction.commit();
        }
    }



    public static int dpToPx(int dimensionInDp)
    {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dimensionInDp, mainActivity.getResources().getDisplayMetrics());
        return px;
    }


    public MainFragment getActiveMainFragment() {
        return activeMainFragment;
    }

    public SlidingFragment getActiveSlidingFragment() {
        return activeSlidingFragment;
    }
}
