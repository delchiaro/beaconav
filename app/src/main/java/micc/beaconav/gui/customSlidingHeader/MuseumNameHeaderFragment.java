package micc.beaconav.gui.customSlidingHeader;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.outdoorEngine.Map;

public class MuseumNameHeaderFragment extends Fragment {

    private TextView textViewMuseumName = null;
    private MuseumRow museumRow = null;
    private Button backBtn = null;

    public MuseumNameHeaderFragment() {
        // Required empty public constructor.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_museum_name_header, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewMuseumName = (TextView)getView().findViewById(R.id.museumName);
        if(museumRow != null) {
            textViewMuseumName.setText(museumRow.getName());
        }
        backBtn = (Button)getView().findViewById(R.id.back_button2);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FragmentHelper.instance().getActiveMainFragment() == FragmentHelper.MainFragment.OUTDOOR)
                {
                    switch(FragmentHelper.instance().getActiveSlidingFragment()){
                        case NAVIGATE:
                            MuseumRow selectedMuseumRow = Map.getIstance().getSelectedMuseumRow();
                            FragmentHelper.instance().simulateDeselectMuseumOnMapClick();
                            FragmentHelper.instance().simulateMuseumOnMapClick(selectedMuseumRow);
                            break;

                        case DETAILS:
                            FragmentHelper.instance().simulateDeselectMuseumOnMapClick();
                            FragmentHelper.instance().showMuseumListFragment();
                            break;

                        case LIST:
                            // void. Nothing to do!
                            break;

                    }

                }
                else if(FragmentHelper.instance().getActiveMainFragment() == FragmentHelper.MainFragment.INDOOR)
                {
                    switch(FragmentHelper.instance().getActiveSlidingFragment()){
                        case NAVIGATE:
                            break;

                        case DETAILS:
                            FragmentHelper.instance().showArtworkListFragment(museumRow);
                            break;

                        case LIST:
                            FragmentHelper.instance().showOutdoorFragment();
                            break;

                    }
                }

            }
        });

    }

    public void setMuseumRow(MuseumRow row){
        this.museumRow = row;
        if(textViewMuseumName != null) {
            textViewMuseumName.setText(museumRow.getName());
        }
    }

}
