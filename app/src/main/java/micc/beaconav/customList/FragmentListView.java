package micc.beaconav.customList;

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

/**
* Created by Mr_Holmes on 21/01/15.
*/
public class FragmentListView extends Fragment {

    ListView listView;
    List<ListItem> listItems;

    public FragmentListView() {}

    //probabilmente questo metodo non serve
    @Override
    public void onAttach(Activity parentActivity) {
        super.onAttach(parentActivity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        return inflater.inflate(R.layout.fragment_list_view, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        listItems = new ArrayList<ListItem>();
        for(int i = 0; i < 20; i++)
        {
            String str = new String();
            str = "Description number " + i;
            ListItem item = new ListItem(R.drawable.graphic, str);
            listItems.add(item);
        }

        listView = (ListView) getView().findViewById(R.id.descriptionList);
        ListAdapter adapter = new ListAdapter(getActivity(), listItems);
        listView.setAdapter(adapter);

    }

    //TODO: implementare l'onClickListener per andare alla descrizione se clickato sul titolo
    //TODO: alla navigazione se clickato su immagine

}