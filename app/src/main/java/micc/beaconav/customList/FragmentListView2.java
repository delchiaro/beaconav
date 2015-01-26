package micc.beaconav.customList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import micc.beaconav.R;


/**
 * A simple {@link android.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FragmentListView2.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FragmentListView2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentListView2 extends Fragment {

    public FragmentListView2() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        View myFragmentView = inflater.inflate(R.layout.fragment_list_view_2, container, false);

        return myFragmentView;
    }

}
