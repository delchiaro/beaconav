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



	public Room(Floor containerFloor)
    {
        this._containerFloor = containerFloor;
        _containerFloor = null;
	}



    //gestione associazione bidirezionale Building - Floor

    public Floor getContainerFloor(){
        return this._containerFloor;
    }
    Room setContainerFloor(Floor floor) //package-private visibility
    {
        this._containerFloor = floor;
        return this;
    }
    void unsetContainerFloor() //package-private visibility
    {
        this._containerFloor = null;
    }
    public final void removeContainerBuilding()
    {
        if(this._containerFloor!=null)
            this._containerFloor.removeRoom(this);
    }







}