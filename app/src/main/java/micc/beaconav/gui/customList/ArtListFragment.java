package micc.beaconav.gui.customList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.IArtRow;
import micc.beaconav.db.dbHelper.artwork.ArtworkRow;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.db.dbJSONManager.JSONHandler;

/**
* Created by Mr_Holmes on 21/01/15.
*/


public class ArtListFragment extends Fragment
{

    private boolean listInflated = false;
    private ListView listView = null;
    private List<IArtRow> artListItems = null;


    public ArtListFragment() {}


    //probabilmente questo metodo non serve
    @Override
    public void onAttach(Activity parentActivity) {
        super.onAttach(parentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_art_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listView = (ListView) getView().findViewById(R.id.descriptionList);
        refreshList();
    }




// * * * * * * * * * * * * * * *  HELPERS * * * * * * * * * * * * * * * * * * * * *

    public void insertRows(final IArtRow[] result) {

        artListItems = new ArrayList<>();
        for (int i = 0; i < result.length; i++) {
            artListItems.add(result[i]);
        }
        inflateList();
    }

    public void refreshList() {
        listInflated = false;
        inflateList();
    }

    private void inflateList()
    {
        if(artListItems != null && listView != null && listInflated == false)
        {
            ListAdapter adapter = new ListAdapter(getActivity(), artListItems);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    IArtRow artRow = artListItems.get(position);

                    if (artRow instanceof MuseumRow) {
                        FragmentHelper.instance().simulateMuseumOnMapClick((MuseumRow) artRow);
                    } else if (artRow instanceof ArtworkRow) {
                        FragmentHelper.instance().showArtworkDetailsFragment((ArtworkRow) artRow);
                    }

                }
            });

            listView.setItemsCanFocus(true);

            listInflated = true;
        }
    }

}