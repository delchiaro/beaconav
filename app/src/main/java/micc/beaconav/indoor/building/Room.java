package micc.beaconav.indoor.building;

import android.graphics.Canvas;

import java.util.ArrayList;

import micc.beaconav.indoor.drawable.DrawableImage;
import micc.beaconav.indoor.spot.Spot;

/**
 * 
 */
public class Room
{
    private ArrayList<Spot> _graphNode = new ArrayList<Spot>();
    private Floor _containerFloor;
    private String _description;


	public Room(Floor containerFloor)
    {
        this._containerFloor = containerFloor;
        containerFloor.addRoom(this);

	}

    public Floor getContainerFloor(){
        return this._containerFloor;
    }



}