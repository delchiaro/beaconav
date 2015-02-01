package micc.beaconav.gui.customList;

import android.view.View;

import micc.beaconav.FragmentHelper;
import micc.beaconav.MainActivity;
import micc.beaconav.R;
import micc.beaconav.db.dbHelper.museum.MuseumRow;

/**
 * Created by nagash on 01/02/15.
 */

public class OnListItemBtnClickListener implements View.OnClickListener
{

    MuseumRow museumRow;
    public void setRow( MuseumRow row){
        this.museumRow = row;
    }

    @Override
    public void onClick(View v) {
        FragmentHelper.getIstance().showMuseumDescrFragment(this.museumRow);
    }
}