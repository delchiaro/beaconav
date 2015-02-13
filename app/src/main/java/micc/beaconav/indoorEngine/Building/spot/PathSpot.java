package micc.beaconav.indoorEngine.building.spot;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import micc.beaconav.indoorEngine.dijkstraSolver.DijkstraNodeAdapter;
import micc.beaconav.indoorEngine.dijkstraSolver.DijkstraStatistics;

/**
 * Created by nagash on 13/02/15.
 */
public class PathSpot extends DrawableSpot implements DijkstraNodeAdapter
{

    @Override
    protected Drawable generateDrawable() {
        return null;
    }

    private List<PathSpot> _linkedSpots = new ArrayList<PathSpot>();

    public void addLinkBidirectional(PathSpot linkedSpot) {
        this._linkedSpots.add(linkedSpot);
        linkedSpot._linkedSpots.add(this);
    }

    public PathSpot(float x, float y) {
        super(x, y);
    }



    @Override
    public double getArchWeight(DijkstraNodeAdapter adjacentNodeAdapter) {
        return 0;
    }

    @Override
    public List<? extends DijkstraNodeAdapter> getAdjacent() {
        //return Arrays.asList(((DijkstraNodeAdapter[])_linkedSpots.toArray(new PathSpot[_linkedSpots.size()])));
        return _linkedSpots;
    }

    @Override
    public DijkstraStatistics getDijkstraStatistic() {
        return null;
    }
}
