package micc.beaconav.indoorEngine.building;

import java.util.ArrayList;

import micc.beaconav.indoorEngine.spot.Spot;

/**
 * 
 */
public class Room
{
    private ArrayList<Spot> _vertices = new ArrayList<Spot>();
    private ArrayList<ConvexArea> _convexAreas = new ArrayList<>();
    private Floor _containerFloor;



	public Room(Floor containerFloor)
    {
        this._containerFloor = containerFloor;
	}

    public void pushVertex(Spot newVertex){
        this._vertices.add(newVertex);
    }
    public int indexOfVertex(Spot vertex){
        return this._vertices.indexOf(vertex);
    }
    public void addCorner(Spot vertex, int index){
        this._vertices.add(index, vertex);
    }

    public Spot getCorner(int index){
        return this._vertices.get(index);
    }



    //gestione associazione bidirezionale Building - Floor

    public Floor getContainerFloor(){
        return this._containerFloor;
    }
    Room setContainerFloor(Floor floor) //package-private visibility
    {
        this._containerFloor = floor;
        return this;
    }
    void unsetContainerFloor() //package-private visibility
    {
        this._containerFloor = null;
    }
    public final void removeContainerBuilding()
    {
        if(this._containerFloor!=null)
            this._containerFloor.removeRoom(this);
    }







}