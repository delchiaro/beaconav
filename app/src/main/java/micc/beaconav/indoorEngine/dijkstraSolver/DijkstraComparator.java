package micc.beaconav.indoorEngine.dijkstraSolver;

import java.util.Comparator;

/**
 * Created by nagash on 13/02/15.
 */

public class DijkstraComparator implements Comparator<DijkstraNodeAdapter> {

    @Override
    public int compare(DijkstraNodeAdapter lhs, DijkstraNodeAdapter rhs) {
        if( lhs.getBestWeight() > rhs.getBestWeight() )
            return 1;
        else if( lhs.getBestWeight() < rhs.getBestWeight() )
            return -1;
        else return 0;
    }
}