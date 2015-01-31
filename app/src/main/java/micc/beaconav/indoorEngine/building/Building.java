package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;

import com.google.android.gms.maps.model.LatLng;

import java.util.TreeMap;

import micc.beaconav.indoorEngine.drawable.DrawableManager;


public class Building
{

  //  LatLng p1;
  //  LatLng p2; // Il Building è un rettangolo definito tra due punti p1 e p2

	private float width; // in  metri
    private float height; // in metri
    private TreeMap<Integer, Floor> floorList;
    private int _activeFloor;

    private final DrawableManager _drawableManager = new DrawableManager();

	public Building(int width, int height)
    {
        this.width = width;
        this.height = height;
        _activeFloor = 0;
        floorList = new TreeMap<Integer, Floor>();
	}




    public float getWidth(){
        return width;
    }
    public float getHeight(){
        return height;
    }



    //gestione associazione bidirezionale Building - Floor
    public final void addFloor(Floor newFloor, int floorIndex)
    {
        newFloor.unsetContainerBuilding();
        floorList.put(floorIndex, newFloor);
        newFloor.setContainerBuilding(this);

        _drawableManager.add(newFloor);
    }
    public final void remove(Floor removedFloor)
    {
        if(removedFloor.getContainerBuilding() != null && removedFloor.getContainerBuilding() == this)
        {
            this.floorList.remove(removedFloor);
            removedFloor.unsetContainerBuilding();

            _drawableManager.remove( removedFloor);
        }
    }



    public int getFloors(){
        return floorList.size();
    }
    public Floor getFloor(int floorIndex){
        return floorList.get(floorIndex);
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

    public DrawableManager getDrawableManager()
    {
        return this._drawableManager;
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