package micc.beaconav.indoorEngine.building;

import android.graphics.Canvas;
import android.graphics.PointF;

import java.util.TreeMap;

import micc.beaconav.indoorEngine.drawable.DrawableManager;
import micc.beaconav.util.containerContained.Container;


public class Building extends Container<Floor>
{

  //  LatLng p1;
  //  LatLng p2; // Il Building è un rettangolo definito tra due punti p1 e p2

	private float width; // in  metri
    private float height; // in metri
    private TreeMap<Integer, Floor> floorList;
    private int _activeFloor;

    private final DrawableManager _drawableManager = new DrawableManager();

	public Building(int width, int height)  {
        this.width = width;
        this.height = height;
        _activeFloor = 0;
        floorList = new TreeMap<Integer, Floor>();
	}


    public float    getWidth(){
        return width;
    }
    public float    getHeight(){
        return height;
    }


    public DrawableManager getDrawableManager()
    {
        return this._drawableManager;
    }



    public void draw(Canvas canvas, int floorIndex) {
        super.get(floorIndex).draw(canvas);
    }
    public void draw(Canvas canvas)
    {
        this.draw(canvas, this._activeFloor);
    }









}