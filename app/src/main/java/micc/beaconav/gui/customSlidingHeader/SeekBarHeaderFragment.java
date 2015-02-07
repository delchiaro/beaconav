package micc.beaconav.gui.customSlidingHeader;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import micc.beaconav.R;

public class SeekBarHeaderFragment extends Fragment {

    public SeekBarHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seekbar_header, container, false);
    }

}
