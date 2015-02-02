package micc.beaconav.gui.customList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.db.dbJSONManager.JSONHandler;

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class ArtListFragment extends Fragment {

    private ListView listView;
    private List<ArtListItem> artListItems;
    private List<MuseumRow> museumRows;
    //private List<ArtPieceRow> artPieceRows; quando ci saranno anche le opere questa riga va attivata

    public ArtListFragment() {}

    //probabilmente questo metodo non serve
    @Override
    public void onAttach(Activity parentActivity) {
        super.onAttach(parentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_art_list, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

      //if(isMuseum == true){

        DbManager.museumDownloader.startDownload();

        DbManager.museumDownloader.addHandler(new JSONHandler<MuseumRow>() {


            @Override
            public void onJSONDownloadFinished(List<MuseumRow> result) {

                museumRows = result;


                artListItems = new ArrayList<ArtListItem>();

                for (int i = 0; i < museumRows.size(); i++) {
                    MuseumRow row = museumRows.get(i);

                    String name = row.getName();
                    ArtListItem item = new ArtListItem(R.drawable.graphic, name);
                    artListItems.add(item);
                }

                listView = (ListView) getView().findViewById(R.id.descriptionList);
                ListAdapter adapter = new ListAdapter(getActivity(), artListItems);
                listView.setAdapter(adapter);
            }
            //else
            //{stesso codice di sopra ma con le opere}
        });

    }

}