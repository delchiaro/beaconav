package micc.beaconav.gui.customList;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import micc.beaconav.R;
import micc.beaconav.db.dbHelper.museum.MuseumRow;
import micc.beaconav.FragmentHelper;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;


public class MuseumDescrFragment extends Fragment
{


    private TextView textViewMuseumDescr = null;
    private MuseumRow museumRow = null;
    private FloatingActionButton toIndoorBtn = null;

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
        if(museumRow != null) {
            textViewMuseumDescr.setText(museumRow.getDescr());
        }

        FragmentHelper.getMainActivity().getFragmentHeaderContainer().setBackgroundColor(FragmentHelper.getMainActivity().getApplicationContext().getResources().getColor(R.color.material_deep_purple));
        FragmentHelper.getMainActivity().getMuseumButton().setColorNormal(FragmentHelper.getMainActivity().getApplicationContext().getResources().getColor(R.color.material_deep_purple));

        toIndoorBtn = (FloatingActionButton)getView().findViewById(R.id.toIndoorBtn);
        toIndoorBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                FragmentHelper.showIndoorMapFragment();
                FragmentHelper.getMainActivity().getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                FragmentHelper.getMainActivity().getFragmentHeaderContainer().setBackgroundColor(FragmentHelper.getMainActivity().getApplicationContext().getResources().getColor(android.R.color.holo_red_light));
                FragmentHelper.getMainActivity().getMuseumButton().setColorNormal(FragmentHelper.getMainActivity().getApplicationContext().getResources().getColor(android.R.color.holo_red_light));
            }
        });

    }


    //Questo setter è fondamentale, al Fragment di quale museo sto parlando
    public void setMuseumRow(MuseumRow row){
        this.museumRow = row;
        if(textViewMuseumDescr != null) {
            textViewMuseumDescr.setText(museumRow.getDescr());
        }
    }


//    public void updateMuseumDescr(String newDescr){
//        if(textViewMuseumDescr != null)
//            textViewMuseumDescr.setText(newDescr);
//    }











}
