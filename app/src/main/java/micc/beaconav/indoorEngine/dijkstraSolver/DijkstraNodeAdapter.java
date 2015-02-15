package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.List;

/**
 * Created by nagash on 05/02/15.
 */
public interface DijkstraNodeAdapter<DNA extends  DijkstraNodeAdapter> {

    public double getArchWeight(DNA adjacentNodeAdapter);
    public List<? extends DijkstraNodeAdapter> getAdjacent();

    void setPathIndex(int index);

    public DijkstraStatistics getDijkstraStatistic();

}