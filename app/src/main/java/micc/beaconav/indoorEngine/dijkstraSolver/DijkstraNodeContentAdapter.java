package micc.beaconav.indoorEngine.dijkstraSolver;

/**
 * Created by nagash on 05/02/15.
 */
public interface DijkstraNodeContentAdapter<T extends  Number> {
    public T getArchWeight(DijkstraNodeContentAdapter<T> adjacentNodeAdapter);

}