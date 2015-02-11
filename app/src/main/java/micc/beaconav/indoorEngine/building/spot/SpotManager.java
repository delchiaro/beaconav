package micc.beaconav.indoorEngine.building.spot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import micc.beaconav.indoorEngine.building.ConvexArea;
import micc.beaconav.util.containerContained.Contained;
import micc.beaconav.util.containerContained.ContainerContained;

/**
 * Created by nagash on 09/02/15.
 */
public class SpotManager<S extends Spot> extends ContainerContained<ConvexArea, Spot>
{

    private final List<S> _spots = new ArrayList<>();

    public SpotManager(ConvexArea containerConvexArea) {

        containerConvexArea.add( (SpotManager<Spot>) this);
    }



    final public Iterator<S> iterator(){
        return _spots.iterator();
    }





}
