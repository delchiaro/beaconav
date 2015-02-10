package micc.beaconav.indoorEngine.bmpBuilding;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by nagash on 09/02/15.
 */
public class SpotManager<S extends Spot>
{

    private final Floor _containerFloor;
    private final List<S> _spots = new ArrayList<>();

    public SpotManager(Floor containerFloor) {
        this._containerFloor = containerFloor;
    }

    public final Floor getContainerFloor() {
        return _containerFloor;
    }

    //gestione associazione bidirezionale SpotManager - Spot
    final public void addSpot(S newSpot) {
        newSpot.unsetContainerManager();
        _spots.add(newSpot);
        newSpot.setContainerManager(this);
    }
    final public void removeSpot(S removedSpot) {
        if(removedSpot.getContainerManager() != null && removedSpot.getContainerManager() == this)
        {
            this._spots.remove(removedSpot);
            removedSpot.unsetContainerManager();
        }
    }

    final public Iterator<S> iterator(){
        return _spots.iterator();
    }





}
