package micc.beaconav.outdoorEngine;


import micc.beaconav.db.dbHelper.museum.MuseumRow;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface MuseumMarkerManager {

    abstract void onClickMuseumMarker(MuseumRow museumRow);
    abstract void onDeselectMuseumMarker();

}
