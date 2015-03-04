package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.List;

/**
 * Created by Ricardo Del Chiaro & Franco Yang.
 */
public interface DijkstraNodeAdapter<DNA extends  DijkstraNodeAdapter> {

    public double getArchWeight(DNA adjacentNodeAdapter);
    public List<? extends DijkstraNodeAdapter> getAdjacent();

    void setPathIndex(int index);

    public DijkstraStatistics getDijkstraStatistic();

}