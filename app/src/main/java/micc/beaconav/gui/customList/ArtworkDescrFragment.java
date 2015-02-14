package micc.beaconav.gui.customList;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import micc.beaconav.FragmentHelper;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.artwork.ArtworkRow;
import micc.beaconav.db.dbHelper.museum.MuseumRow;


public class ArtworkDescrFragment extends Fragment {


    private TextView textViewArtworkDescr = null;
    private TextView textViewArtistName = null;
    private ArtworkRow artworkRow = null;
    private FloatingActionButton navToArtworkBtn = null;
    private ImageView imageViewArtwork;

    public ArtworkDescrFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artwork_descr, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        textViewArtworkDescr = (TextView)getView().findViewById(R.id.artworkDescription);
        if(artworkRow != null) {
            textViewArtworkDescr.setText(artworkRow.getDescription());
        }


        navToArtworkBtn = FragmentHelper.instance().getMainActivity().getFloatingActionButton();
        navToArtworkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( artworkRow != null && artworkRow instanceof ArtworkRow)
                    //TODO: mostra il precorso fino all'opera
                FragmentHelper.instance().getMainActivity().getSlidingUpPanelLayout().setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
            }
        });

        textViewArtistName = (TextView)getView().findViewById(R.id.artistName);
        textViewArtistName.setText(artworkRow.getArtistName());

        imageViewArtwork = (ImageView)getView().findViewById(R.id.artworkImage);
        imageViewArtwork.setImageDrawable(FragmentHelper.instance().getMainActivity().getResources().getDrawable(artworkRow.getImageId()));

    }


    public void setArtworkRow(ArtworkRow row){
        this.artworkRow = row;
        if(textViewArtworkDescr != null) {
            textViewArtworkDescr.setText(artworkRow.getDescription());
        }
    }



}
