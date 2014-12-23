package micc.beaconav.indoor.spot;


import micc.beaconav.indoor.Drawable;
import micc.beaconav.indoor.localization.IndoorPosition;

/**
 * 
 */
public abstract class Spot extends Drawable {

    private IndoorPosition position;

	public Spot(float x, float y, int floor)
    {
        super(x,y);
        position = new IndoorPosition(x, y, floor);
	}



}