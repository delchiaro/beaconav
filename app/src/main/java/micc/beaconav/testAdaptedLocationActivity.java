package micc.beaconav;


import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.widget.TextView;

import micc.beaconav.localization.GoogleLocalizationAdaptedActivity;
import micc.beaconav.localization.GoogleLocalizationAdapter;

public class testAdaptedLocationActivity extends Activity  implements GoogleLocalizationAdaptedActivity
{


    private TextView mLocationView;
    private GoogleLocalizationAdapter localizationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLocationView = new TextView(this);
        localizationAdapter = new GoogleLocalizationAdapter(this, this);
        setContentView(mLocationView);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Connect the client.
       localizationAdapter.start();
    }

    @Override
    protected void onStop() {
        // Disconnecting the client invalidates it.
        super.onStop();
        localizationAdapter.stop();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLocationView.setText("Location received: " + location.toString());
    }
}
