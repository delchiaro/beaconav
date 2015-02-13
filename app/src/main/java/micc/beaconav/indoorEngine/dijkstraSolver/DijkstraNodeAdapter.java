package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.List;

/**
 * Created by nagash on 05/02/15.
 */
public interface DijkstraNodeAdapter {

    public double getArchWeight(DijkstraNodeAdapter adjacentNodeAdapter);
    public List<? extends DijkstraNodeAdapter> getAdjacent();


    public DijkstraStatistics getDijkstraStatistic();

}