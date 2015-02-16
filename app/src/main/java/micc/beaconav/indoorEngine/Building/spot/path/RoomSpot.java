package micc.beaconav.indoorEngine.building.spot.path;

import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.indoorEngine.building.spot.marker.MarkerSpot;

/**
 * Rappresenta un normale MarkerSpot con la differenza che ogni Room deve avere uno ed un solo RoomSpot
 */
public class RoomSpot extends PathSpot {



    public RoomSpot(float x, float y){
        super(x, y);
    }

    public RoomSpot(float x, float y, Room room_container) {
        super(x, y, room_container);
    }





}
