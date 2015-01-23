package micc.beaconav.indoor.spot;


import android.graphics.PointF;

import java.util.ArrayList;

import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

/**
 * 
 */
public class Spot implements ISpot
{

    protected IndoorPosition _indoorPosition;
    private ArrayList<Spot> _links = new ArrayList<Spot>();

    // private Room roomContainer;

    public Spot(IndoorPosition indPosition){
        _indoorPosition = indPosition;
    }
    public Spot(Position position, int floor){
        _indoorPosition = new IndoorPosition(position, floor);
    }
    public Spot(PointF position, int floor){
        _indoorPosition = new IndoorPosition(position, floor);
    }
    public Spot(int x, int y, int floor){
        _indoorPosition = new IndoorPosition(x, y, floor);
    }



    public float getX(){ return _indoorPosition.X(); }
    public float getY(){ return _indoorPosition.Y(); }
    public int getFloor(){ return _indoorPosition.getFloor(); }
    public Position getPosition(){ return this._indoorPosition.getPosition();}
    public IndoorPosition getIndoorPosition(){ return _indoorPosition; }

    public void setIndoorPosition(IndoorPosition newPosition){
        this._indoorPosition = newPosition;
    }
    public void setX(float x){
        this._indoorPosition.setX(x);
    }
    public void setY(float y){
        this._indoorPosition.setY(y);
    }
    public void setFloor(int floor){
        this._indoorPosition.setFloor(floor);
    }



    public Spot addLink(Spot nodeToLink){
        nodeToLink._links.add(this);
        this._links.add(nodeToLink);
        return this;
    }

    public Spot remLink(Spot nodeToUnlink) {
        nodeToUnlink._links.remove(this);
        this._links.remove(nodeToUnlink);
        return this;
    }



}