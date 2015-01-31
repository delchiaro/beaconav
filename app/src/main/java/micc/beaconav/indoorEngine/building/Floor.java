package micc.beaconav.indoorEngine.building;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import java.util.Iterator;
import java.util.TreeMap;

import micc.beaconav.indoorEngine.drawable.Drawable;
import micc.beaconav.indoorEngine.drawable.DrawableBitmap;
import micc.beaconav.localization.Position;


public class Floor extends Drawable
{
    private TreeMap<String, Room> _rooms;
    private Building _containerBuilding;

	public Floor(Position padding){
        super(Long.MIN_VALUE);
        _rooms = new TreeMap<String, Room>();
        _containerBuilding = null;
    }



    @Override
    protected void _coreDraw(Canvas canvas) {
        Iterator<Room> roomIter = _rooms.values().iterator();
        while(roomIter.hasNext())
        {
            roomIter.next().draw(canvas); //delego disegno ad ogni stanza
        }

    }




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
    public final void removeFromContainerBuilding()
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