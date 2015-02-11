package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PointF;

import java.util.Iterator;
import java.util.TreeMap;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.util.containerContained.ContainerContained;


public class Floor extends ContainerContained<Building, Room>  // extends Drawable
{
    private int _floorIndex = 0;

    // not used for now
    private PointF padding = new PointF(0,0);

    public Floor() {}
	public Floor(int floorIndex) {
        this._floorIndex = floorIndex;
    }
    public Floor(int floorIndex, PointF padding) {
        this._floorIndex = floorIndex;
        this.padding = padding;
    }


    public final int getFloorIndex() {
        return _floorIndex;
    }



    public void draw(Canvas canvas) {
        Iterator<Room> roomIter = super.getIterator();
        while(roomIter.hasNext()) {
            roomIter.next().draw(canvas, padding); //delego disegno ad ogni stanza
        }
    }

    public final Building getContainerBuilding() {
        return super.getContainer();
    }









}