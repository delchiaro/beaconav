package micc.beaconav.indoorEngine.building.spot;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nagash on 13/02/15.
 */
public class PathSpot extends DrawableSpot {
    @Override
    protected Drawable generateDrawable() {
        return null;
    }

    private List<Spot> _linkedSpots = new ArrayList<Spot>();

    public void addLinkBidirectional(PathSpot linkedSpot) {
        this._linkedSpots.add(linkedSpot);
        linkedSpot._linkedSpots.add(this);
    }
    public void addLinkDirect(Spot linkedSpot) {
        this._linkedSpots.add(linkedSpot);
    }

    public PathSpot(float x, float y) {
        super(x, y);
    }
}
