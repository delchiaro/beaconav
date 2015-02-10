package micc.beaconav.indoorEngine.bmpBuilding;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;

import java.util.ArrayList;
import java.util.Iterator;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.vectorBuilding.ConvexArea;
import micc.beaconav.indoorEngine.vectorBuilding.Vertex;

/**
 * 
 */
public class Room
{
    private static final int DPI = 300;
    private Paint wallsPaint;
    private Floor _containerFloor;



	public Room() {
        this._containerFloor = null;


    }






    final public Building getCointainerBuilding() {
        return this.getContainerFloor().getContainerBuilding();
    }

    //gestione associazione bidirezionale Floor - Room

    final public Floor getContainerFloor(){
        return this._containerFloor;
    }
    final Room setContainerFloor(Floor floor) {
        this._containerFloor = floor;
        return this;
    }
    final void unsetContainerFloor() //package-private visibility
    {
        this._containerFloor = null;
    }
    final public void removeFromContainerFloor() {
        if(this._containerFloor!=null)
            this._containerFloor.removeRoom(this);
    }




}