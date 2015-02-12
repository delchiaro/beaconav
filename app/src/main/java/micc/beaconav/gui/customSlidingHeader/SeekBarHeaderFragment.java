package micc.beaconav.gui.customSlidingHeader;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.gui.seekBar.DiscreteSeekBar;
import micc.beaconav.outdoorEngine.Map;

public class SeekBarHeaderFragment extends Fragment {

    Button backBtn = null;
    DiscreteSeekBar discreteSeekBar = null;

    public SeekBarHeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        discreteSeekBar = (DiscreteSeekBar)getView().findViewById(R.id.seekBar);
        DiscreteSeekBar.NumericTransformer numericTransformer = new DiscreteSeekBar.NumericTransformer() {
            @Override
            public int transform(int value) {
                return value*1000-950;
            }
        };
        discreteSeekBar.setNumericTransformer(numericTransformer);

        discreteSeekBar.setOnProgressChangeListener(new DiscreteSeekBar.OnProgressChangeListener() {
            @Override
            public void onProgressChanged(DiscreteSeekBar seekBar, int value, boolean fromUser) {
                Map.getIstance().setCircleRadius(value*1000-950);
            }
        });
    }


}
