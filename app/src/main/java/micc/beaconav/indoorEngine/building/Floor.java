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
    private Integer _floorIndex = null;
    private TreeMap<String, Room> _rooms;
    private Building _containerBuilding;




	public Floor(){
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


	public int getRooms(){
		return _rooms.size();
	}
	public Room getRoom(String roomNam)
    {
		return _rooms.get(roomNam);
	}



    public final int getFloorIndex() {
        return _floorIndex;
    }




    //gestione associazione bidirezionale Building - Floor
    final public Building getContainerBuilding(){
        return this._containerBuilding;
    }
    final Floor setContainerBuilding(Building building, int floorIndex_in_building){
        this._containerBuilding = building;
        this._floorIndex = floorIndex_in_building;
        return this;
    }
    final void unsetContainerBuilding() //package-private visibility
    {
        this._containerBuilding = null;
    }
    final public void removeFromContainerBuilding() {
        if(this._containerBuilding!=null)
            this._containerBuilding.remove(this);
    }



    //gestione associazione bidirezionale Floor - Room
    final public void addRoom(String roomName, Room  room) {
        room.unsetContainerFloor();
        _rooms.put(roomName, room);
        room.setContainerFloor(this);
    }
    final public void removeRoom(Room removedRoom) {
        if(removedRoom.getContainerFloor() != null && removedRoom.getContainerFloor() == this)
        {
            this._rooms.remove(removedRoom);
            removedRoom.unsetContainerFloor();
        }
    }




}