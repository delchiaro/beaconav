package micc.beaconav.util.containerContained;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface IContained<CONTAINER extends IContainer> {


    //gestione associazione bidirezionale Room - ConvexArea

    public CONTAINER    getContainer();
    public void         removeFromContainer();



    public void setContainer(CONTAINER container, Container.Key key);

    void unsetContainer(Container.Key key);

}
