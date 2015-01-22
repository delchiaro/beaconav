package micc.beaconav.indoor.spot;

import micc.beaconav.indoor.drawable.Drawable;
import micc.beaconav.indoor.drawable.DrawableImage;
import micc.beaconav.indoor.localization.IndoorPosition;
import micc.beaconav.indoor.localization.Position;

/**
 * Created by Nagash on 29/12/2014.
 */
public class DrawableSpot extends Spot
{
    private Drawable _drawable;


    public DrawableSpot(Drawable drawable, IndoorPosition position){
        super(position);
        this._drawable = drawable;
    }
    public DrawableSpot(Drawable drawable, Position position, int floor){
        this(drawable, new IndoorPosition(position, floor));
    }
    public DrawableSpot(DrawableImage drawable, int floor){
        super(drawable.getPosition(), floor);
    }


}
