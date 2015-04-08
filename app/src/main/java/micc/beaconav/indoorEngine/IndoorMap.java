package micc.beaconav.indoorEngine;

import android.content.Context;
import android.os.AsyncTask;

import java.util.HashMap;
import java.util.concurrent.Executor;

import micc.beaconav.indoorEngine.building.Building;
import micc.beaconav.indoorEngine.building.spot.Spot;
import micc.beaconav.indoorEngine.databaseLite.BuildingDownloader;
import micc.beaconav.indoorEngine.databaseLite.BuildingDownloaderListener;
import micc.beaconav.indoorEngine.databaseLite.BuildingSqliteHelper;

/**
 * Created by Riccardo Del Chiaro & Franco Yang (25/02/2015)
 */
public class IndoorMap implements BuildingDownloaderListener
{
    private static int PPM = 300; // Pixel Per Meter
    private Context context;
    private Building building;
    private BuildingSqliteHelper buildingSqliteHelper = null;
    //private LocalizationSpotManager _localizationSpot;




    HashMap<Integer, Spot> spot_beacon_map = new HashMap<>();
    HashMap<Integer, Spot> spot_QR_map = new HashMap<>();


    BuildingDownloader downloader;

    public IndoorMap( Context context, String museumUrl ) {
        this.context = context;
        downloader = new BuildingDownloader(context, museumUrl, this);
        //this.building = building;
        downloader.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onDownloadFinished() {
        buildingSqliteHelper = new BuildingSqliteHelper(this.context);


        // build museum building from buildingSqliteHelper

    }
}
