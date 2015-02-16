package micc.beaconav.indoorEngine.building.spot.marker;

/**
 * Created by nagash on 16/02/15.
 */
public interface OnSpotMarkerSelectedListener< MS extends  MarkerSpot> {

    public void onMarkerSpotSelected( MS selectedMarker );
}
