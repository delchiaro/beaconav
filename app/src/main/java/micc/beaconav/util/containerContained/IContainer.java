package micc.beaconav.util.containerContained;


/**
 * Created by nagash on 10/02/15.
 */
public interface IContainer<CONTAINED extends IContained> {



    public void add( CONTAINED newObject);
    public void remove(CONTAINED containedObject);
}
