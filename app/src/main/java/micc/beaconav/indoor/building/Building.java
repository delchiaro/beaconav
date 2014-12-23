package micc.beaconav.indoor.building;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.List;

import micc.beaconav.indoor.Drawable;



public class Building extends Drawable
{

	int width;
    int height;
    List<Floor> floorList;


	public Building()
    {
        super(0,0);
        floorList = new ArrayList<Floor>();
	}


	public int getFloors(){
        return floorList.size();
	}


	public Floor getFloor(int floorIndex)
    {
		return floorList.get(floorIndex);
	}

    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }


    public void draw(Canvas canvas, int floorIndex)
    {
        floorList.get(floorIndex).draw(canvas);
    }

    @Override
    public void draw(Canvas canvas)
    {
        this.draw(canvas, 0);
    }
}