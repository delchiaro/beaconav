package micc.beaconav.indoorEngine.spot;


import android.graphics.PointF;

import java.util.ArrayList;

import micc.beaconav.localization.IndoorPosition;
import micc.beaconav.localization.Position;

/**
 * 
 */
public class SpotGraph implements ISpot
{

    protected IndoorPosition _indoorPosition;
    private ArrayList<SpotGraph> _links = new ArrayList<SpotGraph>();

    // private Room roomContainer;

    public SpotGraph(IndoorPosition indPosition){
        _indoorPosition = indPosition;
    }
    public SpotGraph(Position position, int floor){
        _indoorPosition = new IndoorPosition(position, floor);
    }
    public SpotGraph(PointF position, int floor){
        _indoorPosition = new IndoorPosition(position, floor);
    }
    public SpotGraph(int x, int y, int floor){
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



    public SpotGraph addLink(SpotGraph nodeToLink){
        nodeToLink._links.add(this);
        this._links.add(nodeToLink);
        return this;
    }

    public SpotGraph remLink(SpotGraph nodeToUnlink) {
        nodeToUnlink._links.remove(this);
        this._links.remove(nodeToUnlink);
        return this;
    }



}