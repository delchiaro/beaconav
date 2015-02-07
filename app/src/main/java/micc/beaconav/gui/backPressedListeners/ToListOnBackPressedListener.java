package micc.beaconav.gui.backPressedListeners;

import android.app.Activity;
import android.app.FragmentManager;

import micc.beaconav.FragmentHelper;
import micc.beaconav.MainActivity;
import micc.beaconav.R;
import micc.beaconav.outdoorEngine.Map;

/**
 * Created by Mr_Holmes on 07/02/15.
 */
public class ToListOnBackPressedListener implements OnBackPressedListener {


    @Override
    public void doBack() {

        Map.getIstance().unsetMuseumMarker();
        FragmentHelper.setOnMuseumDescrFragment(false);

    }
}
