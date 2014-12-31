package micc.beaconav.indoor.building;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import micc.beaconav.indoor.drawable.Drawable;
import micc.beaconav.indoor.drawable.DrawableManager;
import micc.beaconav.indoor.localization.Position;


public class Building
{

	private int width;
    private int height;
    private TreeMap<Integer, Floor> floorList;
    private int _activeFloor;

    private DrawableManager _drawableManager;

	public Building(int width, int height)
    {
        this.width = width;
        this.height = height;
        _activeFloor = 0;
        floorList = new TreeMap<Integer, Floor>();
        //_drawableManager = new Drawable.DrawableManager();
	}


	public int getFloors(){
        return floorList.size();
	}
	public Floor getFloor(int floorIndex){
		return floorList.get(floorIndex);
	}
    public void addFloor(Floor newFloor, int floorIndex)
    {
        floorList.put(floorIndex, newFloor);
        newFloor.setContainerBuilding(this);
    }

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public boolean setActiveFloor(int floorIndex){
        if(floorList.get(floorIndex) != null ) {
            this._activeFloor = floorIndex;
            return true;
        }
        else return false;
    }
    public Floor getActiveFloor(){
        return floorList.get(this.getActiveFloorIndex());
    }
    public int getActiveFloorIndex() {
        return this._activeFloor;
    }

    public void draw(Canvas canvas, int floorIndex)
    {
        floorList.get(floorIndex).draw(canvas);
    }
    public void draw(Canvas canvas)
    {
        this.draw(canvas, 0);
    }
}