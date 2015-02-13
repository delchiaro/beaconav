package micc.beaconav.indoorEngine.building.spot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.util.containerContained.Contained;
import micc.beaconav.util.containerContained.ContainerContained;

/**
 * Created by nagash on 09/02/15.
 */
public class SpotManager<S extends Spot> extends ContainerContained<Room, S>
{

    public SpotManager(Room containerRoom) {
        containerRoom.add( (SpotManager<Spot>) this);
    }

    public Room getContainerRoom() {
        return super.getContainer();
    }




}
