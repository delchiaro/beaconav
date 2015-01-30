package micc.beaconav.gui.customList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.MuseumRow;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListView2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListView2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MuseumDescrFragment extends Fragment {

    private TextView textViewMuseumDescr = null;
    private MuseumRow museumRow = null;

    public MuseumDescrFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_museum_descr, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewMuseumDescr = (TextView)getView().findViewById(R.id.museumDescription);
        if(museumRow != null)
            textViewMuseumDescr.setText(museumRow.getDescr());
    }


    //Questo setter è fondamentale, al Fragment di quale museo sto parlando
    public void setMuseumRow(MuseumRow row){
        this.museumRow = row;
        if(textViewMuseumDescr != null)
            textViewMuseumDescr.setText(museumRow.getDescr());
    }


    public void updateMuseumDescr(String newDescr){
        textViewMuseumDescr.setText(newDescr);
    }

}
