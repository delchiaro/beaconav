package micc.beaconav.gui.customList;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.DbManager;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.db.dbJSONManager.JSONHandler;
import micc.beaconav.db.dbJSONManager.schema.TableRow;

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class ArtListFragment extends Fragment {

    private ListView listView;
    private List<ArtListItem> artListItems;
    private ArrayList<MuseumRow> museumRows;
    //private ArrayList<ArtPieceRow> artPieceRows; quando ci saranno anche le opere questa riga va attivata

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

        DbManager.museumDownloader.addHandler(new JSONHandler() {

            @Override
            public void onJSONDownloadFinished(TableRow[] result) {

                //museumRows = Arrays.asList((MuseumRow[]) result);

                museumRows = new ArrayList<MuseumRow>(result.length);
                for(int i=0; i<result.length; i++)
                {
                    museumRows.add(new MuseumRow(result[i]));
                }

                artListItems = new ArrayList<ArtListItem>();

                for(int i = 0; i < museumRows.size(); i++)
                {
                    ArtListItem item = new ArtListItem(R.drawable.graphic, museumRows.get(i).getName(), museumRows.get(i).getDescr());
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