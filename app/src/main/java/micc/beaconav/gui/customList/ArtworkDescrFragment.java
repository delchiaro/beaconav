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


public class ArtworkDescrFragment extends Fragment {


    private ArtworkRow artworkRow = null;
    private FloatingActionButton navToArtworkBtn = null;
    private ImageView imageViewArtwork;
    private TextView textViewArtworkDescr = null;
    private TextView textViewArtistDescr = null;
    private TextView textViewYear = null;
    private TextView textViewLocation = null;
    private TextView textViewArtistName = null;
    private TextView textViewDimensions = null;
    private TextView textViewType = null;

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
        imageViewArtwork = (ImageView)getView().findViewById(R.id.artworkImage);
        textViewArtistName = (TextView)getView().findViewById(R.id.artistName);
        textViewYear = (TextView)getView().findViewById(R.id.year);
        textViewLocation = (TextView)getView().findViewById(R.id.location);
        textViewArtistDescr = (TextView)getView().findViewById(R.id.artistDescription);
        textViewDimensions = (TextView)getView().findViewById(R.id.dimensions);
        textViewType = (TextView)getView().findViewById(R.id.type);



        if(artworkRow != null) {


            textViewArtworkDescr.setText(artworkRow.getDescription());
            imageViewArtwork.setImageDrawable(FragmentHelper.instance().getMainActivity().getResources().getDrawable(artworkRow.getImageId()));
            textViewArtistName.setText("Artista: " + artworkRow.getArtistName());
            textViewYear.setText("Anno: " + artworkRow.getCreationYear());
            textViewLocation.setText("Locazione: " + artworkRow.getLocation());
            textViewArtistDescr.setText(artworkRow.getArtistDescr());
            textViewDimensions.setText("Dimensioni: "+artworkRow.getDimensions());
            textViewType.setText("Tecnica: "+artworkRow.getType());


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

    }


    public void setArtworkRow(ArtworkRow row){
        this.artworkRow = row;
        if(textViewArtworkDescr != null) {
            textViewArtworkDescr.setText(artworkRow.getDescription());
        }
    }



}
