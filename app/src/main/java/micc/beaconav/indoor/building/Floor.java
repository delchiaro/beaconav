package micc.beaconav.indoor.building;

import android.graphics.Bitmap;

import java.util.TreeMap;

import micc.beaconav.indoor.drawable.DrawableBitmap;
import micc.beaconav.indoor.localization.Position;


public class Floor extends DrawableBitmap
{
    private TreeMap<String, Room> _rooms;
    private Building _containerBuilding;

	public Floor(Bitmap bmp, Position padding){
        super(bmp, padding, Long.MIN_VALUE);
        _rooms = new TreeMap<String, Room>();
        _containerBuilding = null;
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





    //gestione associazione bidirezionale Building - Floor
    public Building getContainerBuilding(){
        return this._containerBuilding;
    }
    Floor setContainerBuilding(Building building) //package-private visibility
    {
        this._containerBuilding = building;
        return this;
    }
    void unsetContainerBuilding() //package-private visibility
    {
        this._containerBuilding = null;
    }
    public final void removeContainerBuilding()
    {
        if(this._containerBuilding!=null)
            this._containerBuilding.remove(this);
    }



    //gestione associazione bidirezionale Floor - Room
    public void addRoom(String roomName, Room  room)
    {
        room.unsetContainerFloor();
        _rooms.put(roomName, room);
        room.setContainerFloor(this);
    }
    public final void removeRoom(Room removedRoom)
    {
        if(removedRoom.getContainerFloor() != null && removedRoom.getContainerFloor() == this)
        {
            this._rooms.remove(removedRoom);
            removedRoom.unsetContainerFloor();
        }
    }





	public int getRooms(){
		return _rooms.size();
	}
	public Room getRoom(String roomNam)
    {
		return _rooms.get(roomNam);
	}



}