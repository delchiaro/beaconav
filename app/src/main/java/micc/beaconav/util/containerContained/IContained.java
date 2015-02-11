package micc.beaconav.util.containerContained;

/**
 * Created by nagash on 10/02/15.
 */
public interface IContained<CONTAINER extends IContainer> {


    //gestione associazione bidirezionale Room - ConvexArea

    public CONTAINER    getContainer();
    public void         removeFromContainer();



    public void setContainer(CONTAINER container, Container.Key key);

    void unsetContainer(Container.Key key);

}
