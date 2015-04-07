package micc.beaconav.indoorEngine;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;

import micc.beaconav.indoorEngine.databaseLite.BuildingDownloader;

/**
 * Created by nagash on 15/03/15.
 */
public class indoorMapFragment2 extends Fragment
{

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        IndoorMap map = new IndoorMap(this.getActivity(), "http://trinity.micc.unifi.it/museumapp/database.sqlite");

    }

}
