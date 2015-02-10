package micc.beaconav.gui.customSlidingHeader;

import android.os.Bundle;
import android.app.Fragment;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;

public class SeekBarHeaderFragment extends Fragment {

    View myFragmentView = null;
    Button backBtn = null;
    public SeekBarHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        if (myFragmentView == null)
//        {
//            try
//            {
//                myFragmentView = inflater.inflate(R.layout.fragment_seekbar_header, container, false);
//            }
//            catch (InflateException e) { /* map is already there, just return view as it is */ }
//
//        }
//        return myFragmentView;
   // super(inflater, container, savedInstanceState);

        return inflater.inflate(R.layout.fragment_seekbar_header, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        backBtn = (Button)getView().findViewById(R.id.back_button);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentHelper.instance().getMainActivity().getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
            }
        });

    }


}
