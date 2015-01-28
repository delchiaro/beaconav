package micc.beaconav.customList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import micc.beaconav.MainActivity;
import micc.beaconav.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListView2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListView2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MuseumDescrFragment extends Fragment {

    private TextView textViewMuseumDescr;

    public MuseumDescrFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragmentView = inflater.inflate(R.layout.fragment_museum_descr, container, false);

        return myFragmentView;

    }

    public void updateMuseumDescr(String newDescr)
    {
        TextView textViewMuseumDescr = (TextView)getView().findViewById(R.id.museumDescription);
        textViewMuseumDescr.setText(newDescr);
    }

}
