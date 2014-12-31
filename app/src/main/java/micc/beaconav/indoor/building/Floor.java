package micc.beaconav.indoor.building;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import micc.beaconav.indoor.drawable.DrawableBitmap;
import micc.beaconav.indoor.localization.Position;


public class Floor extends DrawableBitmap
{
    List<Room> roomList;
    Building _containerBuilding;

	public Floor(android.graphics.Bitmap bmp, Position position){
        super(bmp, position, -1000);
        roomList = new ArrayList<Room>();
    }


//    public void drawVectorial(Canvas canvas){
//        Iterator<Room> roomIterator = roomList.iterator();
//        while(roomIterator.hasNext())
//        {
//            Room room = roomIterator.next();
//            room.draw(canvas);
//        }
//
//    }






    public Building getContainerBuilding(){
        return this._containerBuilding;
    }

    void setContainerBuilding(Building building) //package-private visibility
    {
        this._containerBuilding = building;
    }

	public int getRooms(){
		return roomList.size();
	}
	public Room getRoom(int index)
    {
		return roomList.get(index);
	}

    public boolean addRoom(Room newRoom){
        if(newRoom.getContainerFloor() == this)
        {
            roomList.add(newRoom);
            return true;
        }
        else return false;
    }

}