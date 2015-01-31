package micc.beaconav.outdoorEngine;


import micc.beaconav.db.dbHelper.museum.MuseumRow;

/**
 * Created by nagash on 27/01/15.
 */
public interface MuseumMarkerManager {

    abstract void onClickMuseumMarker(MuseumRow museumRow);
    abstract void onDeselectMuseumMarker();

}
