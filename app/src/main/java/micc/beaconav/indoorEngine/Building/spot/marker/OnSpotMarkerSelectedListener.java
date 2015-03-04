package micc.beaconav.indoorEngine.building.spot.marker;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 *
 */
public interface OnSpotMarkerSelectedListener< MS extends  MarkerSpot> {

    public void onMarkerSpotSelected( MS selectedMarker );
    public void onNullMarkerSpotSelected( );

}
