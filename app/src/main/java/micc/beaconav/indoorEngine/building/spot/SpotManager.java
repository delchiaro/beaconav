package micc.beaconav.indoorEngine.building.spot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;


import micc.beaconav.indoorEngine.building.Room;
import micc.beaconav.util.containerContained.Contained;
import micc.beaconav.util.containerContained.Container;
import micc.beaconav.util.containerContained.ContainerContained;

/**
 * Created by nagash on 09/02/15.
 */
public class SpotManager<S extends Spot>
{


    LinkedHashSet<S> _containedSpots;

    public SpotManager() {
        _containedSpots = new LinkedHashSet<>();
    }

    public SpotManager( Collection<S> init) {
        _containedSpots = new LinkedHashSet<>(init);
    }


    public void resetAllTranslationAndScale() {
        Iterator<S> iter = iterator();
        while(iter.hasNext())
            iter.next().resetTranslationAndScale();
    }
    public boolean addAll(Collection<S> spots) {
       return this._containedSpots.addAll(spots);
    }
    public boolean add(S spot) {
        return this._containedSpots.add(spot);
    }

    public boolean remove(S spot) {
        return this._containedSpots.remove(spot);
    }

    public boolean contains(S spot) {
        return this._containedSpots.contains(spot);
    }

    public int size() {
        return this._containedSpots.size();
    }

    public Iterator<S> iterator() {
        return _containedSpots.iterator();
    }


}
