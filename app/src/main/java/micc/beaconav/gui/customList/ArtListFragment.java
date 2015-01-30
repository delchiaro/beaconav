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
import micc.beaconav.db.dbHelper.MuseumRow;

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class ArtListFragment extends Fragment {

    private ListView listView;
    private List<ArtListItem> artListItems;
    private ArrayList<MuseumRow> museumRows;
    //List<ArtPieceRow> artPieceRows; quando ci saranno anche le opere questa riga va attivata

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

        artListItems = new ArrayList<ArtListItem>();

        for(int i = 0; i < 20; i++)
        {
            String str = "Description number " + i;
            ArtListItem item = new ArtListItem(R.drawable.graphic, str);
            artListItems.add(item);
        }

        listView = (ListView) getView().findViewById(R.id.descriptionList);
        ListAdapter adapter = new ListAdapter(getActivity(), artListItems);
        listView.setAdapter(adapter);

    }

    //TODO: implementare l'onClickListener per andare alla descrizione se clickato sul titolo
    //TODO: alla navigazione se clickato su immagine

}