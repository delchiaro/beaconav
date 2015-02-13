package micc.beaconav.indoorEngine.building.spot;

import android.graphics.PointF;
import android.graphics.drawable.Drawable;

/**
 * Created by nagash on 13/02/15.
 */
public class DoorSpot extends PathSpot {

    @Override
    protected Drawable generateDrawable() {
        return null;
    }

    private DoorSpot _linkedDoor;

    public DoorSpot(float x, float y, DoorSpot linkedDoor) {
        super(x, y);

        if(linkedDoor != null)
        {
            if (linkedDoor != null && linkedDoor._linkedDoor == null)
            {
                _linkedDoor = linkedDoor;
                linkedDoor._linkedDoor = this;
            }
            else
            {
                //TODO:  exception
            }
        }
        else this._linkedDoor = null;

    }
    public DoorSpot(float x, float y) {
        this(x, y, null);
    }


}
