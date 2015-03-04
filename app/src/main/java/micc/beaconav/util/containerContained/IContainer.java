package micc.beaconav.util.containerContained;


/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */


public interface IContainer<CONTAINED extends IContained> {



    public void add( CONTAINED newObject);
    public void remove(CONTAINED containedObject);
}
